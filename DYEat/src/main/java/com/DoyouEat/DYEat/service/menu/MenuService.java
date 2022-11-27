package com.DoyouEat.DYEat.service.menu;


import com.DoyouEat.DYEat.domain.DYE_Account;
import com.DoyouEat.DYEat.domain.DYE_Images;
import com.DoyouEat.DYEat.repository.menu.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    @Transactional
    public void saveAccount(DYE_Images dye_images){
        menuRepository.save(dye_images);
    }

    public DYE_Images findById(Long id){
      return menuRepository.findOne(id);
    }

}
