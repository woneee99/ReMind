package com.example.enjoytrip.api.controller;

import com.example.enjoytrip.api.dto.MailPostDto;
import com.example.enjoytrip.api.dto.UserDto;
import com.example.enjoytrip.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<UserDto> getUser(Principal p) {
        System.out.println("p = " + p);
        Authentication principal = SecurityContextHolder.getContext().getAuthentication();

        String username = principal.getName();
        UserDto user = userService.getUser(username);
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
    public ResponseEntity<Integer> withdraw(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserDto user = userService.getUser(username);
        int ret = userService.withdraw(user.getUserSeq());
        if(ret == 1) {
            new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
            return ResponseEntity.ok().body(ret);
        }
        else return ResponseEntity.notFound().build();
    }

    @PostMapping("/email")
    public ResponseEntity<Integer> emailSend(@RequestBody MailPostDto email){
        System.out.println("email = " + email);
        int ret = userService.createMailAndChangePassword(email.getEmail());
        return ResponseEntity.ok().body(ret);
    }
}
