package com.example.enjoytrip.api.controller;

import com.example.enjoytrip.api.dto.TripPlanDto;
import com.example.enjoytrip.api.service.TripPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/plan")
public class TripPlanController {

    @Autowired
    TripPlanService tripPlanService;

    private static final int SUCCESS = 1;

    @PostMapping("")
    public ResponseEntity<String> planInsert(@RequestBody TripPlanDto tripPlanDto) {
        int result = tripPlanService.insertTripPlan(tripPlanDto);
        if (result == SUCCESS) {
            return ResponseEntity.ok("여행 계획 저장 완료");
        }
        return ResponseEntity.notFound().build();
    }
}
