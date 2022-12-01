package com.DoyouEat.DYEat.service.event;

import com.DoyouEat.DYEat.domain.DYE_Event;
import com.DoyouEat.DYEat.domain.DYE_Images;
import com.DoyouEat.DYEat.domain.DYE_Menu;
import com.DoyouEat.DYEat.repository.event.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    @Transactional
    public void saveMenus(DYE_Event dye_event){
        eventRepository.save(dye_event);
    }


    public DYE_Event findById(Long id){
        return eventRepository.findOne(id);
    }

    public List<DYE_Event> findAll(Long id){
        return eventRepository.findAll(id);
    }
}
