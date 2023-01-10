package com.DoyouEat.DYEat.repository.account;


import com.DoyouEat.DYEat.domain.DYE_Account;
import com.DoyouEat.DYEat.domain.DYE_Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AccountApiRepository extends JpaRepository<DYE_Account,Long> {

    DYE_Account findByUsername(String username);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE DYE_Account o SET o.picture = :picture WHERE o.id = :id")
    void updatePicture(@Param("picture") String picture, @Param("id") Long id);

    boolean existsDYE_AccountByUsername(String username);

}
