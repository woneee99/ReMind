package com.example.enjoytrip.dao;

import com.example.enjoytrip.dto.HotPlaceDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HotPlaceDao {
    int hotPlaceInsert(HotPlaceDto hotPlaceDto);
}
