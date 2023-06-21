package com.example.enjoytrip.api.service;

import com.example.enjoytrip.api.dto.MailDto;
import com.example.enjoytrip.api.dto.UserDto;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface UserService {
    int register(UserDto userDto);
    UserDto updateInfo(UserDto userDto);
    int withdraw(int userId);

    UserDto getUser(String userId);
    UserDto getUserInfo(int userSeq) throws IOException;

    int createMailAndChangePassword(String userEmail);
    String getTempPassword();
    void mailSend(MailDto mailDTO);
}
