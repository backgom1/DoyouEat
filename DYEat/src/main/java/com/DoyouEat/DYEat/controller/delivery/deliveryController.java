package com.DoyouEat.DYEat.controller.delivery;

import com.DoyouEat.DYEat.domain.*;
import com.DoyouEat.DYEat.repository.delivery.DeliveryApiRepository;
import com.DoyouEat.DYEat.repository.menu.menuFile.MenuFile;
import com.DoyouEat.DYEat.repository.order.OrderApiRepository;
import com.DoyouEat.DYEat.service.delivery.DeliveryService;
import com.DoyouEat.DYEat.service.kakao.KakaoPay;
import com.DoyouEat.DYEat.service.kakao.KakaoPayApprovalVO;
import com.DoyouEat.DYEat.service.kakao.MenuVO;
import com.DoyouEat.DYEat.service.menu.MenuService;
import com.DoyouEat.DYEat.service.order.OrderService;
import com.DoyouEat.DYEat.service.security.CustomDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class deliveryController {

    private final OrderService orderService;
    private final MenuService menuService;
    private final KakaoPay kakaopay;
    private final MenuFile menuFile;

    private final DeliveryApiRepository deliveryApiRepository;

    private final OrderApiRepository orderApiRepository;
    private final DeliveryService deliveryService;

    //결제 정보 띄우는것
    @GetMapping("/pay")
    public String payment(@AuthenticationPrincipal CustomDetails principalDetails, Model model, @ModelAttribute DeliveryForm deliveryForm,
                          @ModelAttribute PayForm payForm) {
        Long principal_delivery_id = principalDetails.getAccount().getId();
        log.info("long={}", principal_delivery_id);
        List<DYE_Orders> all = orderService.findAccount(principal_delivery_id);
        List<DYE_Menu> dye_menus = menuService.findAll(principal_delivery_id);
        List<DYE_Orders> dye_orders = orderApiRepository.statusFind(principal_delivery_id);
        model.addAttribute("Status",dye_orders);
        model.addAttribute("deli_id", principal_delivery_id);
        model.addAttribute("greeting", principalDetails.getAccount().getNickname());
        model.addAttribute("MyOrder", all);
        model.addAttribute("MyMenu", dye_menus);
        return "views/delivery/pay";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        //해당 경로에 있는 파일 경로를 잡는것
        return new UrlResource("file:" + menuFile.getFullPath(filename));
    }

    @GetMapping("/kakaoPay")
    public void kakaoPayGet() {
    }

    @PostMapping("/kakaoPay")
    public String kakaoPay(@AuthenticationPrincipal CustomDetails principalDetails, @ModelAttribute DeliveryForm deliveryForm,
                           @ModelAttribute PayForm payForm) {
        log.info("kakaoPay post............................................");
        DYE_Account account = principalDetails.getAccount();
        Long id = principalDetails.getAccount().getId();
        DYE_Delivery deliveryFindById = deliveryService.deliveryFindById(id);

        DYE_Delivery dye_delivery = new DYE_Delivery();
        List<DYE_Orders> dye_orders = orderService.findAll(id);

        //0 배송 준비중 , 1 배송 진행중 , 2 배송 도착
        dye_delivery.setStatus(0);

        dye_delivery.setStreet(deliveryForm.getStreet());
        dye_delivery.setAddress(deliveryForm.getAddress());
        dye_delivery.setName(deliveryForm.getUserName());
        dye_delivery.setCount(deliveryForm.getCount());
        dye_delivery.setPrice(deliveryForm.getPrice());
        dye_delivery.setDye_orders(dye_orders);
        dye_delivery.setDYEAccount(account);
        orderApiRepository.updateDelivery_Code(deliveryFindById,id);
        deliveryApiRepository.save(dye_delivery);

        return "redirect:" + kakaopay.kakaoPayReady(principalDetails, deliveryForm, payForm);

    }

    @GetMapping("/kakaoPaySuccess")
    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model, @AuthenticationPrincipal CustomDetails principalDetails, @ModelAttribute DeliveryForm deliveryForm,
                                  @ModelAttribute PayForm payForm) {
        log.info("값4={}", deliveryForm.getStreet());
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token, principalDetails);
        model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token, principalDetails, deliveryForm, payForm));

        DYE_Account account = principalDetails.getAccount();
        Long id = principalDetails.getAccount().getId();
        DYE_Payment dye_payment = new DYE_Payment();
        DYE_Delivery dye_delivery = new DYE_Delivery();

        dye_payment.setPayWay("KakaoPay");
        dye_payment.setPayCheck(true);
        dye_payment.setDYEAccount(account);
        dye_payment.setDye_delivery(dye_delivery);

        //1 삭제처리 즉 장바구니에 띄우지 않음
        orderApiRepository.updateStatus(1,id);

        deliveryService.savePay(dye_payment);
        return "views/test/kakaoPaySuccess";
    }


}
