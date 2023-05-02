package com.example.enjoytrip.service;

import com.example.enjoytrip.dao.LoginDao;
import com.example.enjoytrip.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    LoginDao loginDao;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDto login(UserDto userDto) {
        if(userDto.getUserPassword() == null || userDto.getUserEmail() == null) return null;

        String encodePwd = loginDao.login(userDto).getUserPassword();
        System.out.println(encodePwd);
        if(passwordEncoder.matches(userDto.getUserPassword(), encodePwd)){
            userDto.setUserPassword(encodePwd);
            return userDto;
        }
        else return null;

    }
}
