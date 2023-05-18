package com.example.enjoytrip.service;

import com.example.enjoytrip.dto.UserDto;

public interface UserService {
    int register(UserDto userDto);
    UserDto updateInfo(UserDto userDto);
    int withdraw(int userId);

    UserDto getUser(String userId);
}
