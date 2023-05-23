package com.example.enjoytrip.api.service;

import com.example.enjoytrip.api.dto.UserDto;

public interface LoginService {
    UserDto login(UserDto userDto);
    int findPwd(String userEmail);
}
