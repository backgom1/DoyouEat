package com.DoyouEat.DYEat.controller.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class orderController {

    @GetMapping
    public String orderList() {
        return "views/order/orderList";
    }


    @GetMapping("/Detail")
    public String orderDetail() {
        return "views/order/orderDetail";
    }


}
