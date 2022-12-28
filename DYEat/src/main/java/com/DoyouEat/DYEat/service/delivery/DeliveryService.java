package com.DoyouEat.DYEat.service.delivery;


import com.DoyouEat.DYEat.domain.DYE_Delivery;
import com.DoyouEat.DYEat.domain.DYE_Event;
import com.DoyouEat.DYEat.domain.DYE_Payment;
import com.DoyouEat.DYEat.repository.delivery.DeliveryRepository;
import com.DoyouEat.DYEat.repository.event.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Transactional
    public void saveDelivery(DYE_Delivery dye_delivery){
        deliveryRepository.deliverySave(dye_delivery);
    }


    public DYE_Delivery deliveryFindById(Long id){
        return deliveryRepository.deliveryFindOne(id);
    }

    public List<DYE_Delivery> deliveryFindAll(Long id){
        return deliveryRepository.deliveryFindAll(id);
    }

    //pay

    @Transactional
    public void savePay(DYE_Payment dye_payment){
        deliveryRepository.paySave(dye_payment);
    }

    public DYE_Payment payFindById(Long id){
        return deliveryRepository.payFindOne(id);
    }
}
