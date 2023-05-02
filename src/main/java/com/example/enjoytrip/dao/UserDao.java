package com.example.enjoytrip.dao;

import com.example.enjoytrip.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    int register(UserDto userDto);
}
