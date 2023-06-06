package com.example.enjoytrip.api.controller;

import com.example.enjoytrip.api.service.HotPlaceService;
import com.example.enjoytrip.api.dto.HotPlaceDto;
import com.example.enjoytrip.api.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/v1/hotplace")
public class HotPlaceController {

    @Autowired
    HotPlaceService hotPlaceService;

    @PostMapping
    public ResponseEntity<Integer> hotplaceInsert(@RequestBody HotPlaceDto hotPlaceDto, HttpSession session){
        UserDto userDto = (UserDto) session.getAttribute("userDto");
        hotPlaceDto.setUserId(userDto.getUserSeq());

//        hotPlaceDto.setUserId(10);
        return ResponseEntity.ok().body(hotPlaceService.hotPlaceInsert(hotPlaceDto));
    }

    @GetMapping
    public ResponseEntity<List<HotPlaceDto>> hotplaceGet(HttpSession session){
        UserDto userDto = (UserDto) session.getAttribute("userDto");

        return ResponseEntity.ok().body(hotPlaceService.hotPlaceSelect(userDto.getUserSeq()));
    }

    @DeleteMapping("/{hotplaceId}")
    public ResponseEntity<Integer> hotplaceDelete(@PathVariable int hotplaceId, HttpSession session){
        UserDto userDto = (UserDto) session.getAttribute("userDto");

        return ResponseEntity.ok().body(hotPlaceService.hotPlaceDelete(hotplaceId));
    }

}
