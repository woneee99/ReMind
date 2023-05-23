package com.example.enjoytrip.api.service;

import com.example.enjoytrip.api.dto.MailDto;
import com.example.enjoytrip.api.dto.UserDto;

public interface UserService {
    int register(UserDto userDto);
    UserDto updateInfo(UserDto userDto);
    int withdraw(int userId);

    UserDto getUser(String userId);
    int createMailAndChangePassword(String userEmail);
    String getTempPassword();
    void mailSend(MailDto mailDTO);
}
