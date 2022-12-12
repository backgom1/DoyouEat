package com.DoyouEat.DYEat.controller.delivery;

import com.DoyouEat.DYEat.domain.DYE_Menu;
import com.DoyouEat.DYEat.domain.DYE_Orders;
import com.DoyouEat.DYEat.repository.menu.menuFile.MenuFile;
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


    //결제 정보 띄우는것
    @GetMapping("/pay")
    public String payment(@AuthenticationPrincipal CustomDetails principalDetails, Model model) {
        Long principal_delivery_id = principalDetails.getAccount().getId();
        log.info("long={}", principal_delivery_id);
        List<DYE_Orders> all = orderService.findAccount(principal_delivery_id);
        List<DYE_Menu> dye_menus = menuService.findAll(principal_delivery_id);
        model.addAttribute("deli_id",principal_delivery_id);
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
    public String kakaoPay(@AuthenticationPrincipal CustomDetails principalDetails) {
        log.info("kakaoPay post............................................");
        return "redirect:" + kakaopay.kakaoPayReady(principalDetails);

    }

    @GetMapping("/kakaoPaySuccess")
    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model,@AuthenticationPrincipal CustomDetails principalDetails) {
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token,principalDetails);
        model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token,principalDetails));

        return "views/test/kakaoPaySuccess";
    }


}
