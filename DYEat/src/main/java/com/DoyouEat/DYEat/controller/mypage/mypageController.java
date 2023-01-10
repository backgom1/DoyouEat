package com.DoyouEat.DYEat.controller.mypage;


import com.DoyouEat.DYEat.domain.DYE_Account;
import com.DoyouEat.DYEat.domain.DYE_Delivery;
import com.DoyouEat.DYEat.domain.DYE_Orders;
import com.DoyouEat.DYEat.repository.account.AccountApiRepository;
import com.DoyouEat.DYEat.repository.delivery.DeliveryApiRepository;
import com.DoyouEat.DYEat.repository.menu.menuFile.MenuFile;
import com.DoyouEat.DYEat.service.delivery.DeliveryService;
import com.DoyouEat.DYEat.service.order.OrderService;
import com.DoyouEat.DYEat.service.security.CustomDetails;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class mypageController {

    private final AccountApiRepository accountApiRepository;
    private final DeliveryApiRepository deliveryApiRepository;
    private final OrderService orderService;

    private final MenuFile menuFile;

    @Value("${file.dir}")
    private String fileDir;
    @GetMapping
    public String mypage(@AuthenticationPrincipal CustomDetails principalDetails, Model model) {
        Long id = principalDetails.getAccount().getId();
        DYE_Account account = principalDetails.getAccount();
        List<DYE_Orders> dye_orders = orderService.findAll(id);
        model.addAttribute("accountList", account);
        model.addAttribute("orderList", dye_orders);
        model.addAttribute("greeting", principalDetails.getAccount().getNickname());
        model.addAttribute("img", principalDetails.getAccount().getPicture());
        return "/views/mypage/mypage";
    }

    @GetMapping("/update")
    public String updateMypage(@ModelAttribute MyPageForm form,@AuthenticationPrincipal CustomDetails principalDetails,@RequestParam MultipartFile mainFile) throws IOException {
        Long id = principalDetails.getAccount().getId();

        String originalFilename = mainFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);

        String fullPath = fileDir + storeFileName;


        accountApiRepository.updatePicture(fullPath,id);
        mainFile.transferTo(new File(fullPath));
        return "redirect:/mypage";
    }

    @GetMapping("/delete")
    public String deleteMypage(@AuthenticationPrincipal CustomDetails principalDetails, Model model) {
        Long id = principalDetails.getAccount().getId();
        DYE_Account dye_account = principalDetails.getAccount();
        model.addAttribute("account", dye_account);

        accountApiRepository.deleteById(id);
        return "redirect:/main";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        //해당 경로에 있는 파일 경로를 잡는것
        return new UrlResource("file:"+menuFile.getFullPath(filename));
    }

    private String createStoreFileName(String originalFilename) {
        //image.png면
        String s = UUID.randomUUID().toString();
        String ext = extractExt(originalFilename);
        //서버에 저장하는 파일명


        return s + "." + ext;
    }

    //확장자를 띄어주는 작업
    private String extractExt(String originalFilename) {
        int ps = originalFilename.lastIndexOf(".");
        return originalFilename.substring(ps + 1);

    }

}
