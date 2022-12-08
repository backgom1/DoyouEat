package com.DoyouEat.DYEat.controller.main;

import com.DoyouEat.DYEat.domain.DYE_Menu;
import com.DoyouEat.DYEat.repository.menu.MenuApiRepository;
import com.DoyouEat.DYEat.repository.menu.menuFile.MenuFile;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.MalformedURLException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {

    private final MenuApiRepository menuApiRepository;
    private final MenuFile menuFile;

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping
    public String main(Model model) {
        List<DYE_Menu> dyeMenuList = menuApiRepository.findAll();
        model.addAttribute("filePath",fileDir);
        model.addAttribute("menuData",dyeMenuList);
        return "views/main/index";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        //해당 경로에 있는 파일 경로를 잡는것
        return new UrlResource("file:"+menuFile.getFullPath(filename));
    }
}
