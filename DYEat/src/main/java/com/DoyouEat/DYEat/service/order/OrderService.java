package com.DoyouEat.DYEat.service.order;

import com.DoyouEat.DYEat.domain.DYE_Images;
import com.DoyouEat.DYEat.domain.DYE_Menu;
import com.DoyouEat.DYEat.domain.DYE_Orders;
import com.DoyouEat.DYEat.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final OrderRepository orderRepository;

    @Transactional
    public void saveOrders(DYE_Orders dye_orders){
        orderRepository.save(dye_orders);
    }

    public DYE_Orders findById(Long id){
        return orderRepository.findOne(id);
    }


    public DYE_Orders ordersFindOne(Long id) {
        return orderRepository.ordersFindOne(id);
    }
}

