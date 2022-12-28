package com.DoyouEat.DYEat.service.kakao;


import com.DoyouEat.DYEat.controller.delivery.DeliveryForm;
import com.DoyouEat.DYEat.controller.delivery.PayForm;
import com.DoyouEat.DYEat.domain.DYE_Images;
import com.DoyouEat.DYEat.domain.DYE_Menu;
import com.DoyouEat.DYEat.domain.DYE_Orders;
import com.DoyouEat.DYEat.service.menu.MenuService;
import com.DoyouEat.DYEat.service.order.OrderService;
import com.DoyouEat.DYEat.service.security.CustomDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class KakaoPay {

    private static final String HOST = "https://kapi.kakao.com";

    private KakaoPayReadyVO kakaoPayReadyVO;
    private KakaoPayApprovalVO kakaoPayApprovalVO;

    private PayForm payDto;

    private DeliveryForm deliveryDto;

    private final MenuService menuService;

    private final OrderService orderService;

    public String kakaoPayReady(@AuthenticationPrincipal CustomDetails principalDetails, @ModelAttribute DeliveryForm deliveryForm,
                                @ModelAttribute PayForm payForm) {

        Long id = principalDetails.getAccount().getId();
        DYE_Menu dye_menu = menuService.menuFindOne(id);
        DYE_Orders dye_orders = orderService.findById(id);

        log.info("값={}", dye_menu);
        log.info("값2={}", dye_orders);
        log.info("값3={}", deliveryForm.getStreet());

        RestTemplate restTemplate = new RestTemplate();
        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "2481f5eb69fadbcc6870b730d673f14a");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", "gorany");
        params.add("item_name", dye_menu.getTitle());
        params.add("quantity", "1");
        params.add("total_amount", String.valueOf(dye_orders.getPrice()));
        params.add("tax_free_amount", "0");
        params.add("approval_url", "http://localhost:8080/delivery/kakaoPaySuccess");
        params.add("cancel_url", "http://localhost:8080/delivery/pay");
        params.add("fail_url", "http://localhost:8080/delivery/pay/fail");



        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyVO.class);

            return kakaoPayReadyVO.getNext_redirect_pc_url();

        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "/delivery/kakaoPaySuccess";

    }


    public KakaoPayApprovalVO kakaoPayInfo(String pg_token, @AuthenticationPrincipal CustomDetails principalDetails, @ModelAttribute DeliveryForm deliveryForm,
                                           @ModelAttribute PayForm payForm) {

        log.info("값4={}", deliveryForm.getStreet());

        Long id = principalDetails.getAccount().getId();
        DYE_Orders dye_orders = orderService.findById(id);

        RestTemplate restTemplate = new RestTemplate();
        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "2481f5eb69fadbcc6870b730d673f14a");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayReadyVO.getTid());
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", "gorany");
        params.add("pg_token", pg_token);
        params.add("total_amount", String.valueOf(dye_orders.getPrice()));

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalVO.class);


            return kakaoPayApprovalVO;

        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

}

