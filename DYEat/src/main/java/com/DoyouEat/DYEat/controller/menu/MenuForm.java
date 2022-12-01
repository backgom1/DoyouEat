package com.DoyouEat.DYEat.controller.menu;

import com.DoyouEat.DYEat.domain.DYE_Images;
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
    private String picture;
    private int category;
    private List<DYE_Images> dyeImageFiles;
}
