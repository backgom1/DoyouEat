package com.DoyouEat.DYEat.repository.menu.menuFile;


import com.DoyouEat.DYEat.domain.DYE_Images;
import com.DoyouEat.DYEat.domain.DYE_Menu;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class MenuFile {

    @Value("${file.dir}")
    private String fileDir;

    //전체 경로를 받아오는 메서드
    public String getFullPath(String fileName){
        return fileDir + fileName;
    }

    //여러개의 이미지를 받는 메서드
    public List<DYE_Images> storeFiles(List<MultipartFile> multipartFileList) throws IOException {
        List<DYE_Images> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFileList){
            if(!multipartFile.isEmpty()){
                DYE_Images uploadFile = storeFile(multipartFile);
                storeFileResult.add(uploadFile);
            }
        }
        return storeFileResult;
    }

    public DYE_Images storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }
        String originalName = multipartFile.getOriginalFilename();

        String saveName = createStoreFileName(originalName);
        multipartFile.transferTo(new File(getFullPath(saveName)));
        return new DYE_Images(originalName,saveName);
    }

    //파일 이름 만드는 작업을 합니다.
    private String createStoreFileName(String originalName) {
        //image.png면
        String s = UUID.randomUUID().toString();
        String ext = extractExt(originalName);
        //서버에 저장하는 파일명


        return s + "." + ext;
    }

    //확장자를 띄어주는 작업
    private String extractExt(String originalName) {
        int ps = originalName.lastIndexOf(".");
        return originalName.substring(ps + 1);

    }

}
