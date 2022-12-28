package com.DoyouEat.DYEat.service.account;

import com.DoyouEat.DYEat.domain.DYE_Account;
import com.DoyouEat.DYEat.repository.account.AccountApiRepository;
import com.DoyouEat.DYEat.repository.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    private final AccountApiRepository accountApiRepository;

    @Transactional
    public void saveAccount(DYE_Account account){
    accountRepository.save(account);
    }




}
