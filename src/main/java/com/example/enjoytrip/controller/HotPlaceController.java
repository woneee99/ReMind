package com.example.enjoytrip.controller;

import com.example.enjoytrip.dto.HotPlaceDto;
import com.example.enjoytrip.dto.UserDto;
import com.example.enjoytrip.service.HotPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/hotplace")
public class HotPlaceController {

    @Autowired
    HotPlaceService hotPlaceService;

    @PostMapping
    public ResponseEntity<Integer> hotplaceInsert(@RequestBody HotPlaceDto hotPlaceDto, HttpSession session){
        UserDto userDto = (UserDto) session.getAttribute("userDto");
        hotPlaceDto.setUserId(userDto.getUserId());

//        hotPlaceDto.setUserId(10);
        return ResponseEntity.ok().body(hotPlaceService.hotPlaceInsert(hotPlaceDto));
    }

}
