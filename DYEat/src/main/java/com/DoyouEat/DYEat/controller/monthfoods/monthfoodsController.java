package com.DoyouEat.DYEat.controller.monthfoods;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/monthfood")
public class monthfoodsController {

    @GetMapping
    public String monthfoods(){
        return "/views/monthfoods/monthfoods";
    }
}
