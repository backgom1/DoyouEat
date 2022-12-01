package com.DoyouEat.DYEat.controller.menu;

import com.DoyouEat.DYEat.domain.DYE_Account;
import com.DoyouEat.DYEat.domain.DYE_Images;
import com.DoyouEat.DYEat.domain.DYE_Menu;
import com.DoyouEat.DYEat.repository.menu.ImagesApiRepository;
import com.DoyouEat.DYEat.repository.menu.MenuApiRepository;
import com.DoyouEat.DYEat.repository.menu.MenuRepository;
import com.DoyouEat.DYEat.repository.menu.menuFile.MenuFile;
import com.DoyouEat.DYEat.service.account.AccountService;
import com.DoyouEat.DYEat.service.menu.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MenuController {

    private final ImagesApiRepository imagesApiRepository;

    private final MenuService menuService;
    private final MenuApiRepository menuApiRepository;
    private final MenuFile menuFile;

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/order/new")
    public String newItem(@ModelAttribute ImageForm itemForm,@ModelAttribute MenuForm menuForm){
        return "/views/order/orderNewList";
    }

    @PostMapping("/order/new")
    public String saveItem(@RequestParam MultipartFile mainFile, @ModelAttribute ImageForm itemForm, @ModelAttribute MenuForm menuForm) throws IOException {
        List<DYE_Images> storeImageFiles = menuFile.storeFiles(itemForm.getImageFiles());

        DYE_Menu dye_menu = new DYE_Menu();
        dye_menu.setTitle(menuForm.getTitle());
        dye_menu.setText(menuForm.getText());
        dye_menu.setPrice(menuForm.getPrice());
        dye_menu.setType(menuForm.getType());
        String fullPath = fileDir + mainFile.getOriginalFilename();
        String Original = mainFile.getOriginalFilename();
        mainFile.transferTo(new File(fullPath));
        dye_menu.setPicture(Original);
        dye_menu.setMenu_images(storeImageFiles);
        menuService.saveMenus(dye_menu);


        return "redirect:/order/list";
    }

    //페이지 리스트 띄우기
    @GetMapping("/order/list")
    public String MenuList(Model model){
        List<DYE_Images> dyeImagesList = imagesApiRepository.findAll();
        List<DYE_Menu> dyeMenuList = menuApiRepository.findAll();
        model.addAttribute("filePath",fileDir);
        model.addAttribute("menuData",dyeMenuList);
        return "/views/order/orderList";
    }


    //디테일 페이지로 이동
    @GetMapping("/order/{id}/detail")
    public String items(@ModelAttribute ImageForm itemForm, @PathVariable Long id , Model model) throws IOException {
        DYE_Menu dye_menu = menuService.menuFindOne(id);
        MenuForm form= new MenuForm();
        form.setTitle(dye_menu.getTitle());
        form.setPrice(dye_menu.getPrice());
        form.setText(dye_menu.getText());
        List<DYE_Images> menu_images = dye_menu.getMenu_images();
        log.info(String.valueOf(menu_images));
        model.addAttribute("menuId",dye_menu);
        return "/views/order/orderDetail";
    }

    //파일 이미지를 저장하는 메서드 -> 스프링에서 자체적으로 지원을 해준다.
    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        //해당 경로에 있는 파일 경로를 잡는것
        return new UrlResource("file:"+menuFile.getFullPath(filename));
    }

}
