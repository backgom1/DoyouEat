package com.DoyouEat.DYEat.repository.delivery;

import com.DoyouEat.DYEat.domain.DYE_Delivery;
import com.DoyouEat.DYEat.domain.DYE_Event;
import com.DoyouEat.DYEat.domain.DYE_Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DeliveryRepository {

    private final EntityManager em;

    public void deliverySave(DYE_Delivery dye_delivery){
        em.persist(dye_delivery);
    }

    public DYE_Delivery deliveryFindOne(Long id){
        return em.find(DYE_Delivery.class,id);
    }

    public List<DYE_Delivery> deliveryFindAll(Long id){
        return em.createQuery("select a from DYE_Delivery a",DYE_Delivery.class)
                .getResultList();
    }


    //pay

    public void paySave(DYE_Payment dye_payment){
        em.persist(dye_payment);
    }

    public DYE_Payment payFindOne(Long id){
        return em.find(DYE_Payment.class,id);
    }
}
