package com.example.enjoytrip.oauth2.service;

import com.example.enjoytrip.api.dao.UserDao;
import com.example.enjoytrip.api.dto.UserDto;
import com.example.enjoytrip.oauth2.entity.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = userDao.findUser(username);
        if(userDto == null) {
            throw new UsernameNotFoundException("Can not find username.");
        }
        return UserPrincipal.create(userDto);
    }
}
