package com.example.enjoytrip.dao;

import com.example.enjoytrip.dto.HotPlaceDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HotPlaceDao {
    int hotPlaceInsert(HotPlaceDto hotPlaceDto);
    List<HotPlaceDto> hotPlaceSelect(int userId);
    int hotPlaceDelete(int hotPlaceId);
}
