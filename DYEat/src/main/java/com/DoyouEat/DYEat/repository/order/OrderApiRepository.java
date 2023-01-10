package com.DoyouEat.DYEat.repository.order;

import com.DoyouEat.DYEat.domain.DYE_Delivery;
import com.DoyouEat.DYEat.domain.DYE_Images;
import com.DoyouEat.DYEat.domain.DYE_Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderApiRepository extends JpaRepository<DYE_Orders, Long> {

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE DYE_Orders o SET o.status = :status WHERE o.DYEAccount.id = :id")
    void updateStatus(@Param("status") int status,@Param("id") Long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE DYE_Orders o SET o.dye_delivery = :delivery WHERE o.DYEAccount.id = :id")
    void updateDelivery_Code(@Param("delivery") DYE_Delivery delivery,@Param("id") Long id);

    @Query("SELECT o FROM DYE_Orders o WHERE o.status = 0 and o.DYEAccount.id = :id")
    List<DYE_Orders> statusFind(@Param("id") Long id);

}
