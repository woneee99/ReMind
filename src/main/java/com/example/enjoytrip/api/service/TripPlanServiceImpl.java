package com.example.enjoytrip.api.service;

import com.example.enjoytrip.api.dao.TripPlanDao;
import com.example.enjoytrip.api.dto.TripPlanDto;
import com.example.enjoytrip.api.dto.TripPlanSpotDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class TripPlanServiceImpl implements TripPlanService {

    @Autowired
    TripPlanDao tripPlanDao;

    private static final int SUCCESS = 1;
    private static final int FAIL = -1;

    @Override
    @Transactional
    public int insertTripPlan(@RequestBody TripPlanDto tripPlanDto) {
        try {
            if (tripPlanDao.insertTripPlan(tripPlanDto) == SUCCESS) {
                // 여행 계획 관광지 넣기
                return insertTripPlanSpots(tripPlanDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // FIX: 만들어진 여행 계획 삭제하기(delete 쿼리 작성하기)
            // TripPlanSpotDto에서 TripPlanDto의 pk값을 fk로 가지는 애들 삭제한다.
        }
        return FAIL;
    }

    @Override
    public List<TripPlanDto> getUserPlans(int userSeq) {
        List<TripPlanDto> temp = tripPlanDao.getUserPlans(userSeq);
        System.out.println(temp);
        return temp;
    }

    private int insertTripPlanSpots(TripPlanDto tripPlanDto) {
        int tripPlanId = tripPlanDto.getPlanId();
        for (TripPlanSpotDto spotDto : tripPlanDto.getTripSpots()) {
            spotDto.setPlanId(tripPlanId);
            tripPlanDao.insertTripPlanSpots(spotDto);
        }
        return SUCCESS;
    }
}
