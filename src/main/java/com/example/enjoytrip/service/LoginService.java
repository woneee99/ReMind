package com.example.enjoytrip.service;

import com.example.enjoytrip.dto.UserDto;

public interface LoginService {
    UserDto login(UserDto userDto);
    int findPwd(String userEmail);
}
