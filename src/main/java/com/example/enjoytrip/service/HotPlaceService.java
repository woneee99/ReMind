package com.example.enjoytrip.service;

import com.example.enjoytrip.dto.HotPlaceDto;

import java.util.List;

public interface HotPlaceService {
    int hotPlaceInsert(HotPlaceDto hotPlaceDto);
    List<HotPlaceDto> hotPlaceSelect(int userId);
    int hotPlaceDelete(int hotPlaceId);
}
