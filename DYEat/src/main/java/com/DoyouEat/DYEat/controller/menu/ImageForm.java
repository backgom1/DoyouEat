package com.DoyouEat.DYEat.controller.menu;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ImageForm {

    private Long id;
    private String menuName;
    private int type;
    private List<MultipartFile> imageFiles;
}
