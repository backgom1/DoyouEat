package com.DoyouEat.DYEat.repository.delivery;

import com.DoyouEat.DYEat.domain.DYE_Account;
import com.DoyouEat.DYEat.domain.DYE_Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryApiRepository extends JpaRepository<DYE_Delivery,Long> {
}
