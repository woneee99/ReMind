package com.example.enjoytrip.api.service;

import com.example.enjoytrip.api.dto.HotPlaceDto;

import java.util.List;

public interface HotPlaceService {
    int hotPlaceInsert(HotPlaceDto hotPlaceDto);
    List<HotPlaceDto> hotPlaceSelect(int userId);
    int hotPlaceDelete(int hotPlaceId);
}
