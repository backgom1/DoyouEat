package com.DoyouEat.DYEat.controller.order;

import com.DoyouEat.DYEat.domain.DYE_Account;
import com.DoyouEat.DYEat.domain.DYE_Menu;
import com.DoyouEat.DYEat.domain.DYE_Orders;
import com.DoyouEat.DYEat.service.menu.MenuService;
import com.DoyouEat.DYEat.service.order.OrderService;
import com.DoyouEat.DYEat.service.security.CustomDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
public class orderController {

    private final OrderService orderService;
    private final MenuService menuService;

    //주문정보만 받아올때
    @PostMapping("/{id}/buy")
    public String orderList(@PathVariable Long id, @ModelAttribute OrderForm orderForm, @AuthenticationPrincipal CustomDetails principalDetails) {
        log.info(orderForm.getHow());
        log.info(orderForm.getWantdate());
        log.info("price={}",orderForm.getPrice());
        DYE_Orders dye_orders = new DYE_Orders();
        dye_orders.setHow(orderForm.getHow());
        dye_orders.setWantdate(orderForm.getWantdate());
        dye_orders.setPrice(orderForm.getPrice());
        dye_orders.setQuantity(orderForm.getResult());
        DYE_Account account = principalDetails.getAccount();
        DYE_Menu dye_menu = menuService.menuFindOne(id);
        dye_orders.setDYEAccount(account);
        dye_orders.setDYEMenu(dye_menu);
        //현재 접속한 계정의 code를 받아와야한다 findone
        orderService.saveOrders(dye_orders);
        return "redirect:/mybag";
    }




}
