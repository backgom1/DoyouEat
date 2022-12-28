package com.DoyouEat.DYEat.controller.menu;

import com.DoyouEat.DYEat.controller.order.OrderForm;
import com.DoyouEat.DYEat.domain.*;
import com.DoyouEat.DYEat.repository.menu.ImagesApiRepository;
import com.DoyouEat.DYEat.repository.menu.MenuApiRepository;
import com.DoyouEat.DYEat.repository.menu.MenuRepository;
import com.DoyouEat.DYEat.repository.menu.menuFile.MenuFile;
import com.DoyouEat.DYEat.service.account.AccountService;
import com.DoyouEat.DYEat.service.menu.CommentService;
import com.DoyouEat.DYEat.service.menu.MenuService;
import com.DoyouEat.DYEat.service.order.OrderService;
import com.DoyouEat.DYEat.service.security.CustomDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MenuController {

    private final ImagesApiRepository imagesApiRepository;
    private final MenuService menuService;
    private final MenuApiRepository menuApiRepository;
    private final MenuFile menuFile;

    private final CommentService commentService;

    //파일 저장 경로
    @Value("${file.dir}")
    private String fileDir;


    //메뉴 리스트 생성 목록
    @GetMapping("/order/new")
    public String newItem(@ModelAttribute ImageForm itemForm, @ModelAttribute MenuForm menuForm) {
        return "/views/order/orderNewList";
    }

    //메뉴 리스트 저장
    @PostMapping("/order/new")
    public String saveItem(@RequestParam MultipartFile mainFile, @ModelAttribute ImageForm itemForm, @ModelAttribute MenuForm menuForm) throws IOException {
        List<DYE_Images> storeImageFiles = menuFile.storeFiles(itemForm.getImageFiles());
        DYE_Menu dye_menu = new DYE_Menu();

        //페이지 내용
        dye_menu.setTitle(menuForm.getTitle());
        dye_menu.setText(menuForm.getText());
        dye_menu.setPrice(menuForm.getPrice());
        dye_menu.setType(menuForm.getType());

        //이미지
        String fullPath = fileDir + mainFile.getOriginalFilename();
        String Original = mainFile.getOriginalFilename();
        mainFile.transferTo(new File(fullPath));
        dye_menu.setPicture(Original);
        dye_menu.setMenu_images(storeImageFiles);

        //저장
        menuService.saveMenus(dye_menu);

        return "redirect:/order/list";
    }

    //페이지 리스트 띄우기
    @GetMapping("/order/list")
    public String MenuList(Model model) {
        List<DYE_Images> dyeImagesList = imagesApiRepository.findAll();
        List<DYE_Menu> dyeMenuList = menuApiRepository.findAll();
        model.addAttribute("filePath", fileDir);
        model.addAttribute("menuData", dyeMenuList);
        return "/views/order/orderList";
    }


    //디테일 페이지로 이동
    @GetMapping("/order/{id}/detail")
    public String items(@ModelAttribute OrderForm orderForm, @ModelAttribute ImageForm itemForm, @PathVariable Long id, Model model) throws IOException {
        DYE_Menu dye_menu = menuService.menuFindOne(id);
        List<DYE_Review> dye_reviews = commentService.findAll(id);
        MenuForm form = new MenuForm();
        form.setTitle(dye_menu.getTitle());
        form.setPrice(dye_menu.getPrice());
        form.setText(dye_menu.getText());
        model.addAttribute("reviews",dye_reviews);
        model.addAttribute("menuId", dye_menu);
        return "/views/order/orderDetail";
    }

    //파일 이미지를 저장하는 메서드 -> 스프링에서 자체적으로 지원을 해준다.
    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        //해당 경로에 있는 파일 경로를 잡는것
        return new UrlResource("file:" + menuFile.getFullPath(filename));
    }


//    댓글 저장하는 기능
    @ResponseBody
    @PostMapping(value = "/order/review/save",produces = "application/json; charset=UTF-8")
    public String saveComment(@RequestBody Map<String, String> map,@AuthenticationPrincipal CustomDetails principalDetails) {
        DYE_Account account = principalDetails.getAccount();
        DYE_Menu dye_menu = menuService.menuFindOne(Long.parseLong(map.get("boardId")));
        DYE_Review dye_review = new DYE_Review();
        dye_review.setText(map.get("contentvalue"));
        dye_review.setDYEAccount(account);
        dye_review.setDYEMenu(dye_menu);

        commentService.saveComment(dye_review);

        return map.get("contentvalue");
    }
}
