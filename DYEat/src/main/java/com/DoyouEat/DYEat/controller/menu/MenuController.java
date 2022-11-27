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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MenuController {

    private final ImagesApiRepository imagesApiRepository;

    private final MenuApiRepository menuApiRepository;
    private final MenuFile menuFile;

    @GetMapping("/items/new")
    public String newItem(@ModelAttribute ImageForm itemForm,@ModelAttribute MenuForm menuForm){
        return "/views/order/orderNewList";
    }

    @PostMapping("/items/new")
    public String saveItem(@ModelAttribute ImageForm itemForm,@ModelAttribute MenuForm menuForm) throws IOException {
        DYE_Images attachFile = menuFile.storeFile(itemForm.getAttachFile());
        List<DYE_Images> storeImageFiles = menuFile.storeFiles(itemForm.getImageFiles());

        DYE_Menu dye_menu = new DYE_Menu();
        dye_menu.setTitle(menuForm.getTitle());
        dye_menu.setText(menuForm.getText());
        dye_menu.setPrice(menuForm.getPrice());
        dye_menu.setType(menuForm.getType());

        DYE_Images dye_images = new DYE_Images();
        dye_images.setMenuName(itemForm.getMenuName());
        dye_menu.setMenu_images(storeImageFiles);

        menuApiRepository.save(dye_menu);
        imagesApiRepository.save(dye_images);

        return "redirect:/views/order/orderList";
    }
}
