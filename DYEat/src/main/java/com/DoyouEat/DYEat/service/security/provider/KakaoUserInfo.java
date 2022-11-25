package com.DoyouEat.DYEat.service.security.provider;

import java.util.Map;

public class KakaoUserInfo implements OAuth2UserInfo{


    private Map<String, Object> attributes;
    private Map<String, Object> attributesProperties;
    private Map<String, Object> attributesAccount;
    private Map<String, Object> attributesProfile;

    public KakaoUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
        this.attributesProperties = (Map<String, Object>) attributes.get("properties");
        this.attributesAccount = (Map<String, Object>) attributes.get("kakao_account");
        this.attributesProfile = (Map<String, Object>) attributesAccount.get("profile");

    }

    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getEmail() {
        return (String) attributesAccount.get("email");
    }

    @Override
    public String getName() {
        return (String) attributesProperties.get("nickname");
    }


    @Override
    public String getProfile_image() {
        return (String) attributesProfile.get("profile_image_url");
    }
}
