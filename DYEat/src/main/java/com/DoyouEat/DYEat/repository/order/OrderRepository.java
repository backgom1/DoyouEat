package com.DoyouEat.DYEat.repository.order;

import com.DoyouEat.DYEat.domain.DYE_Images;
import com.DoyouEat.DYEat.domain.DYE_Menu;
import com.DoyouEat.DYEat.domain.DYE_Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;


    public void save(DYE_Orders dye_orders){
        em.persist(dye_orders);
    }

    public DYE_Orders findOne(Long id){
        return em.find(DYE_Orders.class,id);
    }

    public List<DYE_Orders> findAll(Long id){
        return em.createQuery("select a from DYE_Orders a",DYE_Orders.class)
                .getResultList();
    }


    public DYE_Orders ordersFindOne(Long id){
        return em.find(DYE_Orders.class,id);
    }
}
