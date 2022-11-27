package com.DoyouEat.DYEat.repository.menu;

import com.DoyouEat.DYEat.domain.DYE_Account;
import com.DoyouEat.DYEat.domain.DYE_Images;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MenuRepository {

    private final EntityManager em;


    public void save(DYE_Images dye_images){
        em.persist(dye_images);
    }

    public DYE_Images findOne(Long id){
        return em.find(DYE_Images.class,id);
    }

    public List<DYE_Images> findAll(Long id){
        return em.createQuery("select a from DYE_Images a",DYE_Images.class)
                .getResultList();
    }
}
