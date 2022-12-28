package com.DoyouEat.DYEat.repository.order;

import com.DoyouEat.DYEat.domain.DYE_Images;
import com.DoyouEat.DYEat.domain.DYE_Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface OrderApiRepository extends JpaRepository<DYE_Orders, Long> {
}
