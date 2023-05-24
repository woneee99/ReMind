package com.example.enjoytrip.api.service;

import com.example.enjoytrip.api.dao.TripPlanDao;
import com.example.enjoytrip.api.dto.TripPlanDto;
import com.example.enjoytrip.api.dto.TripPlanSpotDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

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
            // 만들어졌던 DB rows 삭제
            tripPlanDao.deleteUserPlans(tripPlanDto.getPlanId(), tripPlanDto.getUserSeq());
        }
        return FAIL;
    }

    @Override
    public List<TripPlanDto> getUserPlans(int userSeq) {
        List<TripPlanDto> result = tripPlanDao.getUserPlans(userSeq);
        result.sort(Comparator.comparing(TripPlanDto::getStartDate));
        return result;
    }

    @Override
    public List<List<TripPlanSpotDto>> getUserPlanSpots(int planId) {
        List<TripPlanSpotDto> planSpots = tripPlanDao.getUserPlanSpots(planId);

        Map<String, List<TripPlanSpotDto>> spotMap = new TreeMap<>();

        // 날짜별로 관광지 정보를 그룹화
        for (TripPlanSpotDto spot : planSpots) {
            String tripDate = spot.getTripDate();
            spotMap.computeIfAbsent(tripDate, k -> new ArrayList<>()).add(spot);
        }

        // 날짜 오름차순으로 정렬된 2차원 배열 생성
        List<List<TripPlanSpotDto>> sortedSpots = new ArrayList<>(spotMap.values());

        // 관광지 정보를 spotOrder를 기준으로 오름차순으로 정렬
        for (List<TripPlanSpotDto> spots : sortedSpots) {
            spots.sort(Comparator.comparingInt(TripPlanSpotDto::getSpotOrder));
        }

        return sortedSpots;
    }

    @Override
    public int deleteUserPlans(int planId, int userSeq) {
        System.out.println("planId = " + planId);
        System.out.println("userSeq = " + userSeq);
        int result = tripPlanDao.deleteUserPlans(planId, userSeq);
        System.out.println("result = " + result);
        if (result > 0) return SUCCESS;
        return FAIL;
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
