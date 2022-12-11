package com.DoyouEat.DYEat.controller.delivery;

import com.DoyouEat.DYEat.domain.DYE_Menu;
import com.DoyouEat.DYEat.domain.DYE_Orders;
import com.DoyouEat.DYEat.repository.menu.menuFile.MenuFile;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.MalformedURLException;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class deliveryController {

    private final OrderService orderService;
    private final MenuService menuService;

    private final MenuFile menuFile;


    @GetMapping("/pay")
    public String payment(@AuthenticationPrincipal CustomDetails principalDetails, Model model) {
        Long principal_delivery_id = principalDetails.getAccount().getId();
        log.info("long={}",principal_delivery_id);
        List<DYE_Orders> all = orderService.findAccount(principal_delivery_id);
        List<DYE_Menu> dye_menus = menuService.findAll(principal_delivery_id);
        model.addAttribute("greeting",principalDetails.getAccount().getNickname());
        model.addAttribute("MyOrder", all);
        model.addAttribute("MyMenu", dye_menus);
        return "views/delivery/pay";
    }

        @ResponseBody
        @GetMapping("/images/{filename}")
        public Resource downloadImage (@PathVariable String filename) throws MalformedURLException {
            //해당 경로에 있는 파일 경로를 잡는것
            return new UrlResource("file:" + menuFile.getFullPath(filename));
        }
    }
