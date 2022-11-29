package com.DoyouEat.DYEat.controller.menu;

import com.DoyouEat.DYEat.domain.DYE_Account;
import com.DoyouEat.DYEat.domain.DYE_Images;
import com.DoyouEat.DYEat.domain.DYE_Menu;
import com.DoyouEat.DYEat.repository.menu.ImagesApiRepository;
import com.DoyouEat.DYEat.repository.menu.MenuApiRepository;
import com.DoyouEat.DYEat.repository.menu.MenuRepository;
import com.DoyouEat.DYEat.repository.menu.menuFile.MenuFile;
import com.DoyouEat.DYEat.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MenuController {

    private final ImagesApiRepository imagesApiRepository;

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
        dye_menu.setMenu_images(storeImageFiles);
        String fullPath = fileDir + mainFile.getOriginalFilename();
        mainFile.transferTo(new File(fullPath));
        dye_menu.setPicture(fullPath);
        menuApiRepository.save(dye_menu);


        return "redirect:/order/list";
    }

    @GetMapping("/order/list")
    public String MenuList(Model model){
        List<DYE_Images> dyeImagesList = imagesApiRepository.findAll();
        List<DYE_Menu> dyeMenuList = menuApiRepository.findAll();
        return "/views/order/orderList";
    }
}
