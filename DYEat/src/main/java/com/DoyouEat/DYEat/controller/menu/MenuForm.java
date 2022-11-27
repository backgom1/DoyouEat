package com.DoyouEat.DYEat.controller.menu;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class MenuForm {
    private Long id;
    private String title;
    private String text;
    private int price;
    private int type;
}
