package com.example.enjoytrip.api.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TripPlanSpotDto {
    private int tripSpotId;
    private int planId;
    private String tripDate;
    private String spotName;
    private int spotId;
    private double spotLa;
    private double spotMa;
    private String spotAddress;
    private String spotPhone;
    private int departureTime;
    private int duration;
    private int moveTime;
    private int spotOrder;
}
