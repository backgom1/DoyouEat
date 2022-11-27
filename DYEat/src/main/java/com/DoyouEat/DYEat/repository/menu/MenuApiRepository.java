package com.DoyouEat.DYEat.repository.menu;

import com.DoyouEat.DYEat.domain.DYE_Account;
import com.DoyouEat.DYEat.domain.DYE_Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuApiRepository extends JpaRepository<DYE_Menu,Long> {


}
