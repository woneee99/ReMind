package com.example.enjoytrip.api.service;

import com.example.enjoytrip.api.dto.TripPlanDto;
import com.example.enjoytrip.api.dto.TripPlanSpotDto;

import java.util.List;

public interface TripPlanService {
    int insertTripPlan(TripPlanDto tripPlanDto);

    List<TripPlanDto> getUserPlans(int userSeq);

    List<TripPlanSpotDto> getUserPlanSpots(int planId);
}
