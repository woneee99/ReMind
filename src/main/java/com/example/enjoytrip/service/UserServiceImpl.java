package com.example.enjoytrip.service;

import com.example.enjoytrip.dao.UserDao;
import com.example.enjoytrip.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public int register(UserDto userDto) {

        if(userDto.getUserPassword() != null) {
            userDto.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));
        }
        return userDao.register(userDto);
    }
}
