package com.DoyouEat.DYEat.repository.event;


import com.DoyouEat.DYEat.domain.DYE_Event;
import com.DoyouEat.DYEat.domain.DYE_Images;
import com.DoyouEat.DYEat.domain.DYE_Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EventRepository {

    private final EntityManager em;


    public void save(DYE_Event dye_event){
        em.persist(dye_event);
    }

    public DYE_Event findOne(Long id){
        return em.find(DYE_Event.class,id);
    }

    public List<DYE_Event> findAll(Long id){
        return em.createQuery("select a from DYE_Event a",DYE_Event.class)
                .getResultList();
    }
}
