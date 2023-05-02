package com.example.enjoytrip.controller;

import com.example.enjoytrip.dto.UserDto;
import com.example.enjoytrip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public int register(@RequestBody UserDto userDto){
        return userService.register(userDto);
    }
}
