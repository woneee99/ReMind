package com.example.enjoytrip.api.service;

import com.example.enjoytrip.api.dao.LoginDao;
import com.example.enjoytrip.api.dto.UserDto;
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

        UserDto loginUser = loginDao.login(userDto);

        String encodePwd = loginUser.getUserPassword();
        System.out.println(encodePwd);
        if(passwordEncoder.matches(userDto.getUserPassword(), encodePwd)){
            return loginUser;
        }
        else return null;

    }

    @Override
    public int findPwd(String userEmail) {
        UserDto userDto = loginDao.findEmail(userEmail);
        System.out.println(userDto);
        if(userDto == null) return 0;
        int pwd = (int) (Math.random() * 99 ) + 1;
        userDto.setUserPassword(String.valueOf(pwd));

        loginDao.updatePwd(userDto);
        return pwd;
    }
}
