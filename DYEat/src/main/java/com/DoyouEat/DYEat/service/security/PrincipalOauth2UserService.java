package com.DoyouEat.DYEat.service.security;

import com.DoyouEat.DYEat.domain.DYE_Account;
import com.DoyouEat.DYEat.repository.account.AccountApiRepository;
import com.DoyouEat.DYEat.service.security.provider.GoogleUserInfo;
import com.DoyouEat.DYEat.service.security.provider.KakaoUserInfo;
import com.DoyouEat.DYEat.service.security.provider.OAuth2UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {


    private final AccountApiRepository accountApiRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("userRequest = " + userRequest);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        OAuth2User oAuth2User = super.loadUser(userRequest);

        OAuth2UserInfo oAuth2UserInfo = null;
        if(userRequest.getClientRegistration().getRegistrationId().equals("google")){
            System.out.println("구글요청");
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else if (userRequest.getClientRegistration().getRegistrationId().equals("kakao")) {
            System.out.println("카카오");
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        } else {
            System.out.println("구글과 카카오만함.");
        }
        String provider = oAuth2UserInfo.getProvider(); //Google
        String providerID = oAuth2UserInfo.getProviderId();
        String username = provider + "_" + providerID;
        String password = passwordEncoder.encode("겟인데어");
        String role = "ROLE_USER";
        String nickname = oAuth2UserInfo.getName();
        String profile_image = oAuth2UserInfo.getProfile_image();

        DYE_Account account = accountApiRepository.findByUsername(username);
        if (account == null) {
            account = DYE_Account.builder()
                    .username(username)
                    .password(password)
                    .nickname(nickname)
                    .role(role)
                    .provider(provider)
                    .providerid(providerID)
                    .picture(profile_image)
                    .build();
            accountApiRepository.save(account);
        }
        return new CustomDetails(account,oAuth2User.getAttributes());
    }
}
