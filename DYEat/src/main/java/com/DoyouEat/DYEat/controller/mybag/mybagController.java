package com.DoyouEat.DYEat.controller.mybag;

import com.DoyouEat.DYEat.domain.DYE_Menu;
import com.DoyouEat.DYEat.domain.DYE_Orders;
import com.DoyouEat.DYEat.repository.menu.MenuApiRepository;
import com.DoyouEat.DYEat.repository.menu.menuFile.MenuFile;
import com.DoyouEat.DYEat.repository.order.OrderApiRepository;
import com.DoyouEat.DYEat.service.menu.MenuService;
import com.DoyouEat.DYEat.service.order.OrderService;
import com.DoyouEat.DYEat.service.security.CustomDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.http.HttpClient;
import java.util.List;

@Controller
@RequestMapping("/mybag")
@RequiredArgsConstructor
public class mybagController {

    private final OrderService orderService;
    private final MenuService menuService;
    private final MenuFile menuFile;

    private final MenuApiRepository menuApiRepository;
    private final OrderApiRepository orderApiRepository;

    @GetMapping
    @Transactional
    public String myBag(@AuthenticationPrincipal CustomDetails principalDetails, Model model){
        Long id = principalDetails.getAccount().getId();
        List<DYE_Orders> all = orderService.findAccount(id);
        List<DYE_Menu> dye_menus = menuService.findAll(id);
        List<DYE_Orders> status_list = orderApiRepository.statusFind(id);
        model.addAttribute("MyOrder",all);
        model.addAttribute("MyMenu",dye_menus);
        model.addAttribute("Status",status_list);
        return "views/mybag/mybag";
    }

    @PostMapping("/delete")
    public String deleteMyBag(@RequestParam("delList") List<Integer> ids){
        for(Integer id : ids) orderApiRepository.deleteById(Long.valueOf(id));
        return "redirect:/mybag";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        //해당 경로에 있는 파일 경로를 잡는것
        return new UrlResource("file:"+menuFile.getFullPath(filename));
    }
}
