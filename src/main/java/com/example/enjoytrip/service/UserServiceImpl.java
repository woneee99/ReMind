package com.example.enjoytrip.service;

import com.example.enjoytrip.dao.UserDao;
import com.example.enjoytrip.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto getUser(String userId) {
        return userDao.findUser(userId);
    }

    @Override
    public int register(UserDto userDto) {

        if(userDto.getUserPassword() != null) {
            userDto.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));
        }
        return userDao.register(userDto);
    }

    @Override
    public UserDto updateInfo(UserDto userDto) {
        userDto.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));
        userDao.updateInfo(userDto);
        userDto.setUpdatedAt(LocalDate.now().toString());
        return userDto;
    }

    @Override
    public int withdraw(int userId) {
        return userDao.withdraw(userId);
    }
}
