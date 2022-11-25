package com.DoyouEat.DYEat.service.security;

import com.DoyouEat.DYEat.domain.DYE_Account;
import com.DoyouEat.DYEat.repository.account.AccountApiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.attribute.UserPrincipal;


// 시큐리티 설정에서 loginProcessingUrl
// 로그인 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행

@Service
@RequiredArgsConstructor
public class CustomDetailsService implements UserDetailsService {

    //시큐리티 session(Authentication(내부 UserDetails)
    private final AccountApiRepository accountApiRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("username = " + username);
        DYE_Account userEntity = accountApiRepository.findByUsername(username);

        if (userEntity == null) {
            throw new UsernameNotFoundException("사용자가 입력한 아이디에 해당하는 사용자를 찾을 수 없습니다.");
        } else {
            return new CustomDetails(userEntity);
        }
    }
}
