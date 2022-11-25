package com.DoyouEat.DYEat.service.security.provider;



public interface OAuth2UserInfo {
    String getProviderId();
    String getProvider();
    String getEmail();
    String getName();
    String getProfile_image();

}
