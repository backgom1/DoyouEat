package com.DoyouEat.DYEat.controller.order;

import com.DoyouEat.DYEat.domain.DYE_Orders;
import com.DoyouEat.DYEat.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
public class orderController {

    private final OrderService orderService;

    //주문정보만 받아올때
    @PostMapping("/buy")
    public String orderList(@ModelAttribute OrderForm orderForm) {
        log.info(orderForm.getHow());
        log.info(orderForm.getWantdate());
        log.info("price={}",orderForm.getPrice());
        DYE_Orders dye_orders = new DYE_Orders();
        dye_orders.setHow(orderForm.getHow());
        dye_orders.setWantdate(orderForm.getWantdate());
        dye_orders.setPrice(orderForm.getPrice());
        //현재 접속한 계정의 code를 받아와야한다 findone
        orderService.saveOrders(dye_orders);
        return "redirect:/mybag";
    }




}
