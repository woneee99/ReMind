package com.example.enjoytrip.api.controller;

import com.example.enjoytrip.api.dto.TripPlanDto;
import com.example.enjoytrip.api.dto.TripPlanSpotDto;
import com.example.enjoytrip.api.dto.UserDto;
import com.example.enjoytrip.api.service.TripPlanService;
import com.example.enjoytrip.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/plan")
public class TripPlanController {

    @Autowired
    TripPlanService tripPlanService;

    @Autowired
    UserService userService;

    private static final int SUCCESS = 1;

    @PostMapping("")
    public ResponseEntity<String> planInsert(@RequestBody TripPlanDto tripPlanDto) {
        int result = tripPlanService.insertTripPlan(tripPlanDto);
        if (result == SUCCESS) {
            return ResponseEntity.ok("여행 계획 저장 완료");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/my-plans")
    public ResponseEntity<List<TripPlanDto>> getUserPlans() {
        Authentication principal = SecurityContextHolder.getContext().getAuthentication();
        String username = principal.getName();
        System.out.println("username = " + username);
        UserDto user = userService.getUser(username);

        System.out.println("내 pk: " + user.getUserSeq());
        List<TripPlanDto> myPlans = tripPlanService.getUserPlans(user.getUserSeq());
        System.out.println("내 계획!!!!!! " + myPlans);

        if (myPlans.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(myPlans);
    }

    @GetMapping("/my-plans/{planId}")
    public ResponseEntity<List<TripPlanSpotDto>> getUserPlanSpots(@PathVariable int planId) {
        List<TripPlanSpotDto> mySpots = tripPlanService.getUserPlanSpots(planId);
        if(mySpots.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mySpots);
    }
}
