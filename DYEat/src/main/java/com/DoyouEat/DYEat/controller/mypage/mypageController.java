package com.DoyouEat.DYEat.controller.mypage;


import com.DoyouEat.DYEat.service.security.CustomDetails;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequestMapping("/mypage")
public class mypageController {

    @GetMapping
    public String mypage(@AuthenticationPrincipal CustomDetails principalDetails, Model model){
        model.addAttribute("greeting",principalDetails.getAccount().getNickname());
        model.addAttribute("img",principalDetails.getAccount().getPicture());
        return "/views/mypage/mypage";
    }

}
