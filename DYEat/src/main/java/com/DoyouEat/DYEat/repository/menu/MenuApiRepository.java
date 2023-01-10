package com.DoyouEat.DYEat.repository.menu;

import com.DoyouEat.DYEat.domain.DYE_Menu;
import com.DoyouEat.DYEat.domain.DYE_Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface MenuApiRepository extends JpaRepository<DYE_Menu, Long> {

    Optional<DYE_Menu> findById(Long id);



}
