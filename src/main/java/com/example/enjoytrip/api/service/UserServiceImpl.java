package com.example.enjoytrip.api.service;

import com.example.enjoytrip.api.dao.UserDao;
import com.example.enjoytrip.api.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    @Transactional(readOnly = true)
    public UserDto getUser(String userId) {
        return userDao.findUser(userId);
    }


    @Override
    @Transactional
    public int register(UserDto userDto) {

        if(userDto.getUserPassword() != null) {
            userDto.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));
        }
        return userDao.register(userDto);
    }


    @Override
    @Transactional
    public UserDto updateInfo(UserDto userDto) {
        userDto.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));
        userDao.updateInfo(userDto);
        userDto.setUpdatedAt(LocalDate.now().toString());
        return userDto;
    }


    @Override
    @Transactional
    public int withdraw(int userId) {
        return userDao.withdraw(userId);
    }
}
