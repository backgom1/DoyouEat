package com.DoyouEat.DYEat.controller.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class mypageController {

    @GetMapping
    public String mypage(){
        return "/views/mypage/mypage";
    }
}
