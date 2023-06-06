package com.example.enjoytrip.api.dao;

import com.example.enjoytrip.api.dto.TripPlanDto;
import com.example.enjoytrip.api.dto.TripPlanSpotDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TripPlanDao {
    int insertTripPlan(TripPlanDto tripPlanDto);

    int insertTripPlanSpots(TripPlanSpotDto tripPlanSpotDto);

    List<TripPlanDto> getUserPlans(int userSeq);

    List<TripPlanSpotDto> getUserPlanSpots(int planId);

    int deleteUserPlans(@Param("planId") int planId, @Param("userSeq") int userSeq);
}
