package com.DoyouEat.DYEat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Configuration
//@EnableWebSecurity
public class SecurityConfig {

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().antMatchers();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http.authorizeRequests()
//                .antMatchers("/main/**").permitAll()
//                .antMatchers("/community/**", "/findteam/**", "/mypage").hasAnyRole("USER")
//                .and()
//                .formLogin()
//                .loginPage("/main/login")
//                .loginProcessingUrl("/main/login.do")
//                .defaultSuccessUrl("/main") // 나중에 그 전 페이지로 redirect하기
//                .failureUrl("/main/login.fail")
//                .usernameParameter("email")
//                .passwordParameter("pw")
//                .successHandler(successHandler)
//                .failureHandler(failureHandler)
//                .and()
//                .logout()
//                .logoutUrl("/main/logout")
//                .logoutSuccessUrl("/main")
//                .deleteCookies("JSESSIONID"/*, "remember-me"*/)
//                .addLogoutHandler(new LogoutHandler() {
//                    @Override
//                    public void logout(HttpServletRequest request,
//                                       HttpServletResponse response,
//                                       Authentication authentication) {
//                        HttpSession session = request.getSession();
//                        session.invalidate();
//                    }
//                })
//                .and()
//                .csrf()
//                .disable()
//                .authenticationProvider(authenticationProvider)
//                .build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

}
