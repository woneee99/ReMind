package com.example.enjoytrip.api.dao;

import com.example.enjoytrip.oauth2.token.UserRefreshToken;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRefreshTokenDao {
    int register(UserRefreshToken userRefreshToken);
    UserRefreshToken findUserRefreshToken(String userId);
    UserRefreshToken findUserRefreshToken2(String userId, String refreshToken);

}
