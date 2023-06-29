package com.example.enjoytrip.api.dao;

import com.example.enjoytrip.api.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    int register(UserDto userDto);
    int updateInfo(String userName, int userSeq);
    int withdraw(int userSeq);
    UserDto findUser(String userId);

    UserDto findUserByUserSeq(int userSeq);

    UserDto findEmailUser(String userEmail);
    int findEmailUpdate(UserDto userDto);
}
