package com.DoyouEat.DYEat.repository.account;


import com.DoyouEat.DYEat.domain.DYE_Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountApiRepository extends JpaRepository<DYE_Account,Long> {

    DYE_Account findByUsername(String username);
    Optional<DYE_Account> findById(Long id);


}
