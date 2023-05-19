package com.example.enjoytrip.api.controller;

import com.example.enjoytrip.api.dto.UserDto;
import com.example.enjoytrip.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<UserDto> getUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDto user = userService.getUser(principal.getUsername());
        System.out.println(user);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/register")
    public ResponseEntity<Integer> register(UserDto userDto){

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

        userDto.setUserSeq(dto.getUserSeq());
        userDto.setUserName(dto.getUserName());
        userDto.setCreatedAt(dto.getCreatedAt());

        UserDto resUser = userService.updateInfo(userDto);

        session.setAttribute("userDto", resUser);
        return ResponseEntity.ok().body(resUser);
    }

    @DeleteMapping("/myInfo")
    public ResponseEntity<Integer> withdraw(HttpSession session){
        UserDto dto = (UserDto) session.getAttribute("userDto");

        int ret = userService.withdraw(dto.getUserSeq());
        if(ret == 1) {
            session.invalidate();
            return ResponseEntity.ok().body(ret);
        }
        else return ResponseEntity.notFound().build();
    }
}
