package com.example.enjoytrip.api.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TripPlanDto {
    private int planId;
    private int userSeq;
    private String planTitle;
    private String startDate;
    private String endDate;
    private List<TripPlanSpotDto> tripSpots;
}
