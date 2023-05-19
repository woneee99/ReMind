package com.example.enjoytrip.api.dao;

import com.example.enjoytrip.api.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginDao {
    UserDto login(UserDto userDto);
    int updatePwd(UserDto userDto);
    UserDto findEmail(String userEmail);
}
