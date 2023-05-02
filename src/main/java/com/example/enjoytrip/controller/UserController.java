package com.example.enjoytrip.controller;

import com.example.enjoytrip.dto.UserDto;
import com.example.enjoytrip.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Integer> register(@RequestBody UserDto userDto){
        userService.register(userDto);
        return ResponseEntity.ok().body(1);
    }

    @GetMapping("/myInfo")
    public ResponseEntity<UserDto> getInfo(HttpSession session){
        UserDto dto = (UserDto) session.getAttribute("userDto");
        if(dto == null) ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/myInfo")
    public ResponseEntity<UserDto> updateInfo(@RequestBody UserDto userDto, HttpSession session){
        UserDto dto = (UserDto) session.getAttribute("userDto");
        if(dto == null) ResponseEntity.notFound().build();

        userDto.setUserId(dto.getUserId());
        userDto.setUserName(dto.getUserName());
        userDto.setCreatedAt(dto.getCreatedAt());

        UserDto resUser = userService.updateInfo(userDto);

        session.setAttribute("userDto", resUser);
        return ResponseEntity.ok().body(resUser);
    }

    @DeleteMapping("/myInfo")
    public ResponseEntity<Integer> withdraw(HttpSession session){
        UserDto dto = (UserDto) session.getAttribute("userDto");

        int ret = userService.withdraw(dto.getUserId());
        if(ret == 1) {
            session.invalidate();
            return ResponseEntity.ok().body(ret);
        }
        else return ResponseEntity.notFound().build();
    }
}
