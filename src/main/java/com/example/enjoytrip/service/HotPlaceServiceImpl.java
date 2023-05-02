package com.example.enjoytrip.service;

import com.example.enjoytrip.dao.HotPlaceDao;
import com.example.enjoytrip.dto.HotPlaceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotPlaceServiceImpl implements HotPlaceService{

    @Autowired
    HotPlaceDao hotPlaceDao;

    @Override
    public int hotPlaceInsert(HotPlaceDto hotPlaceDto) {
        return hotPlaceDao.hotPlaceInsert(hotPlaceDto);
    }

    @Override
    public List<HotPlaceDto> hotPlaceSelect(int userId) {
        return hotPlaceDao.hotPlaceSelect(userId);
    }

    @Override
    public int hotPlaceDelete(int hotPlaceId) {
        return hotPlaceDao.hotPlaceDelete(hotPlaceId);
    }
}
