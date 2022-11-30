package com.DoyouEat.DYEat.repository.menu;

import com.DoyouEat.DYEat.domain.DYE_Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MenuApiRepository extends JpaRepository<DYE_Menu,Long> {

    Optional<DYE_Menu> findById(Long id);



}
