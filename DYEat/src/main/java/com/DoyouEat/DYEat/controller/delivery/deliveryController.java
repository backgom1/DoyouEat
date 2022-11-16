package com.DoyouEat.DYEat.controller.delivery;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/delivery")
public class deliveryController {

    @GetMapping("/pay")
    public String payment(){
        return "/views/delivery/pay";
    }
}
