package com.example.enjoytrip.oauth2.service;

import com.example.enjoytrip.api.dao.UserDao;
import com.example.enjoytrip.api.dto.UserDto;
import com.example.enjoytrip.oauth2.entity.ProviderType;
import com.example.enjoytrip.oauth2.entity.RoleType;
import com.example.enjoytrip.oauth2.entity.UserPrincipal;
import com.example.enjoytrip.oauth2.exception.OAuthProviderMissMatchException;
import com.example.enjoytrip.oauth2.info.OAuth2UserInfo;
import com.example.enjoytrip.oauth2.info.OAuth2UserInfoFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserDao userDao;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);
        try {
            return this.process(userRequest, user);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User process(OAuth2UserRequest userRequest, OAuth2User user) {
        ProviderType providerType = ProviderType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());

        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(providerType, user.getAttributes());
        UserDto savedUser = userDao.findUser(userInfo.getId());


        if (savedUser != null) {
            if (providerType != savedUser.getProviderType()) {
                throw new OAuthProviderMissMatchException(
                        "Looks like you're signed up with " + providerType +
                                " account. Please use your " + savedUser.getProviderType() + " account to login."
                );
            }
            updateUser(savedUser, userInfo);
        } else {
            savedUser = createUser(userInfo, providerType);
        }

        return UserPrincipal.create(savedUser, user.getAttributes());
    }

    private UserDto createUser(OAuth2UserInfo userInfo, ProviderType providerType) {
        LocalDateTime now = LocalDateTime.now();

        UserDto user = new UserDto(
                userInfo.getId(),
                userInfo.getEmail(),
                userInfo.getName(),
                "Y",
                userInfo.getImageUrl(),
                providerType,
                RoleType.USER,
                now.toString(),
                now.toString()
        );
        int ret = userDao.register(user);
        if(ret == 1) {
            return user;
        } else return null;
    }

    private UserDto updateUser(UserDto user, OAuth2UserInfo userInfo) {
        if (userInfo.getName() != null && !user.getUserName().equals(userInfo.getName())) {
            user.setUserName(userInfo.getName());
        }

        if (userInfo.getImageUrl() != null && !user.getProfileImageUrl().equals(userInfo.getImageUrl())) {
            user.setProfileImageUrl(userInfo.getImageUrl());
        }

        return user;
    }
}
