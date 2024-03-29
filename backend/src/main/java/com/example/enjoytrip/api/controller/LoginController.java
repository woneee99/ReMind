package com.example.enjoytrip.api.controller;

import com.example.enjoytrip.api.service.LoginService;
import com.example.enjoytrip.api.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController("/api/v1")
public class LoginController {

    @Autowired
    LoginService service;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody UserDto userDto, HttpSession session){
        UserDto dto = service.login(userDto);

        if(dto != null) {
            session.setAttribute("userDto", dto);
        } else {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/out")
    public ResponseEntity<Integer> logout(HttpSession session){
        session.invalidate();
        return ResponseEntity.ok().body(1);
    }

    @PutMapping("/login")
    public ResponseEntity<Integer> findPwd(@RequestBody Map<String, String> map){
        return ResponseEntity.ok().body(service.findPwd(map.get("userEmail")));
    }
}
