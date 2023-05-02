package com.example.enjoytrip.controller;

import com.example.enjoytrip.dto.UserDto;
import com.example.enjoytrip.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    LoginService service;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody UserDto userDto, HttpSession session){
        UserDto dto = service.login(userDto);
        Map<String, String> map = new HashMap<>();

        if(dto != null) {
            session.setAttribute("userDto", userDto);
            map.put("result", "success");
        }
        else map.put("result", "fail");

        System.out.println(userDto.toString());
        return map;
    }
}
