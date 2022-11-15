package com.DoyouEat.DYEat.controller.faq;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/faq")
public class faqController {

    @GetMapping
    public String FAQPage(){
        return "views/faq/faq";
    }

}
