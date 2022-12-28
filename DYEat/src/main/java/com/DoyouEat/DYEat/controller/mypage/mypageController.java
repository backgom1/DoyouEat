package com.DoyouEat.DYEat.controller.mypage;


import com.DoyouEat.DYEat.domain.DYE_Delivery;
import com.DoyouEat.DYEat.repository.delivery.DeliveryApiRepository;
import com.DoyouEat.DYEat.service.delivery.DeliveryService;
import com.DoyouEat.DYEat.service.security.CustomDetails;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class mypageController {

    private final DeliveryService deliveryService;
    private final DeliveryApiRepository deliveryApiRepository;

    @GetMapping
    public String mypage(@AuthenticationPrincipal CustomDetails principalDetails, Model model) {
        Long id = principalDetails.getAccount().getId();
        List<DYE_Delivery> deliveryList = deliveryService.deliveryFindAll(id);
        model.addAttribute("deliveryList",deliveryList);
        model.addAttribute("greeting", principalDetails.getAccount().getNickname());
        model.addAttribute("img", principalDetails.getAccount().getPicture());
        return "/views/mypage/mypage";
    }

    @GetMapping("/{id}/delete")
    public String deleteMypage(@PathVariable("id") Long id){
      deliveryApiRepository.deleteById(id);
        return "redirect:/main";
    }

}
