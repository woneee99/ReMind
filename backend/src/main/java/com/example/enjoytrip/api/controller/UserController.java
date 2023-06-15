package com.example.enjoytrip.api.controller;

import com.example.enjoytrip.api.dto.MailPostDto;
import com.example.enjoytrip.api.dto.UserDto;
import com.example.enjoytrip.api.service.UserService;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    private String uploadFolder = "C:\\upload";

    @GetMapping
    public ResponseEntity<UserDto> getUser(Principal p) throws IOException {
        System.out.println("p = " + p);
        Authentication principal = SecurityContextHolder.getContext().getAuthentication();

        String username = principal.getName();
        UserDto userDto = userService.getUser(username);

        InputStream inputStream = new FileInputStream(uploadFolder + "/" + userDto.getProfileImageUrl());
        byte[] images = IOUtils.toByteArray(inputStream);
        byte[] byteEnc64 = Base64.encodeBase64(images);
        String imgStr = new String(byteEnc64 , "UTF-8");
        userDto.setProfileImageUrl(imgStr);

        return ResponseEntity.ok().body(userDto);
    }

    @PostMapping("/register")
    public ResponseEntity<Integer> register(UserDto userDto){
        int ret = userService.register(userDto);
        return ResponseEntity.ok().body(ret);
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
