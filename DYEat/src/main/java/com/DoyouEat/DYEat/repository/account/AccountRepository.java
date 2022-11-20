package com.DoyouEat.DYEat.repository.account;

import com.DoyouEat.DYEat.domain.DYE_Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AccountRepository {

    private final EntityManager em;


    public void save(DYE_Account account){
        em.persist(account);
    }

    public DYE_Account findOne(Long id){
        return em.find(DYE_Account.class,id);
    }

    public List<DYE_Account> findAll(Long id){
        return em.createQuery("select a from DYE_Account a",DYE_Account.class)
                .getResultList();
    }
}
