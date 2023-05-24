package com.example.enjoytrip.api.dao;

import com.example.enjoytrip.api.dto.TripPlanDto;
import com.example.enjoytrip.api.dto.TripPlanSpotDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TripPlanDao {
    int insertTripPlan(TripPlanDto tripPlanDto);

    int insertTripPlanSpots(TripPlanSpotDto tripPlanSpotDto);
}
