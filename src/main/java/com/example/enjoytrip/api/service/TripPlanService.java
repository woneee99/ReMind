package com.example.enjoytrip.api.service;

import com.example.enjoytrip.api.dto.TripPlanDto;

import java.util.List;

public interface TripPlanService {
    int insertTripPlan(TripPlanDto tripPlanDto);
    List<TripPlanDto> getUserPlans(int userSeq);
}
