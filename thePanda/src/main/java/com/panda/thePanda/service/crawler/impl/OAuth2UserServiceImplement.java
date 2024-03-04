package com.panda.thePanda.service.crawler.impl;

import java.util.Map;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.panda.thePanda.entity.CustomOAuth2User;
import com.panda.thePanda.entity.UserEntity;
import com.panda.thePanda.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImplement extends DefaultOAuth2UserService {
  private final UserRepository userRepository;

  @SuppressWarnings("null")
  @Override
  public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(request);
    String oauthClientName = request.getClientRegistration().getClientName();
    try {
      System.out.println(new ObjectMapper().writeValueAsString(oAuth2User.getAttributes()));
      // System.out.println(oauthClientName);
    } catch (Exception e) {
      e.printStackTrace();
    }
    UserEntity userEntity = null;
    String userId = null;
    String email = null;
    if (oauthClientName.equals("kakao")) {
      userId = "kakao_" + oAuth2User.getAttributes().get("id");
      email = "kakao_" + oAuth2User.getAttributes().get("email");
      System.out.println(email);
      userEntity = new UserEntity(userId, email, oauthClientName);
    }
    if (oauthClientName.equals("naver")) {
      @SuppressWarnings("unchecked")
      Map<String, String> responseMap = (Map<String, String>) oAuth2User.getAttributes().get("response");
      userId = "naver_" + responseMap.get("id").substring(0, 14);
      email = responseMap.get("email");
      userEntity = new UserEntity(userId, email, oauthClientName);
    }
    if (oauthClientName.equals("Google")) {
      userId = "google_" + oAuth2User.getAttributes().get("sub");
      email = "google_" + oAuth2User.getAttributes().get("email");
      userEntity = new UserEntity(userId, email, oauthClientName);
    }

    userRepository.save(userEntity);
    return new CustomOAuth2User(userId);
  }
}