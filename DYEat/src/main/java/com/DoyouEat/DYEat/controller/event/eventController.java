package com.DoyouEat.DYEat.controller.event;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/event")
public class eventController {

    @GetMapping
    public String event(){

        return "/views/event/event";
    }
}
