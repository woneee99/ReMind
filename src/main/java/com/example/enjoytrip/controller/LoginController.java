package com.example.enjoytrip.controller;

import com.example.enjoytrip.dto.UserDto;
import com.example.enjoytrip.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService service;

    @PostMapping
    public ResponseEntity<UserDto> login(@RequestBody UserDto userDto, HttpSession session){
        UserDto dto = service.login(userDto);

        if(dto != null) {
            session.setAttribute("userDto", dto);
        } else {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public ResponseEntity<Integer> logout(HttpSession session){
        session.invalidate();
        return ResponseEntity.ok().body(1);
    }
}
