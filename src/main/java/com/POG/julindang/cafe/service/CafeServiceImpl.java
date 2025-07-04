package com.POG.julindang.cafe.service;


import com.POG.julindang.cafe.dto.response.beverage.BeverageAndDessertListDto;
import com.POG.julindang.cafe.dto.response.cafe.CafeLikeResponseDto;
import com.POG.julindang.cafe.dto.response.cafe.CafeResponseDto;
import com.POG.julindang.cafe.dto.response.common.CommonResponseDto;
import com.POG.julindang.cafe.repository.CafeImageRepository;
import com.POG.julindang.cafe.repository.CafeRepository;
import com.POG.julindang.cafe.repository.DessertRepository;
import com.POG.julindang.cafe.util.JwtUtil;
import com.POG.julindang.common.exception.beverage.ParameterInvalidException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class
CafeServiceImpl implements CafeService {
    private final CafeImageRepository cafeImageRepository;
    private final CafeRepository cafeRepository;
    private final DessertRepository dessertRepository;


    // 카페 정보 불러오기
    @Override
    @Transactional(readOnly = true)
    public List<CafeLikeResponseDto> findCafeNames(){
        return cafeImageRepository.getAllCafeImages(JwtUtil.getMemberId());
    }

    // 카페 검색
    @Override
    @Transactional(readOnly = true)
    public List<CafeResponseDto> findByCafeName(String cafeName){
        return cafeImageRepository.getCafeName(cafeName);
    }

    //즐겨찾기 한 카페
    @Override
    @Transactional(readOnly = true)
    public List<CafeLikeResponseDto> findCafeNamesByBookmark() {
        return cafeImageRepository.getLikedCafeImages(JwtUtil.getMemberId());
    }

    //즐겨찾기 하지 않은 카페
    @Override
    @Transactional(readOnly = true)
    public List<CafeLikeResponseDto> findCafeNamesByNotBookmark() {
        return cafeImageRepository.getNotLikedCafeImages(JwtUtil.getMemberId());
    }

    @Override
    public BeverageAndDessertListDto getBeveragesAndDesserts(Long sort) {

        List<CommonResponseDto> beverages;
        List<CommonResponseDto> desserts;

        if(sort == 0) {
            //get beverages
            beverages = cafeRepository.getMaxSugarBeverageDesc();
            //get desserts
            desserts = dessertRepository.getDessertListDesc();

            // 두 리스트를 합친다
            List<CommonResponseDto> combinedList = new ArrayList<>();
            combinedList.addAll(desserts);
            combinedList.addAll(beverages);

            // sugar 값에 따라 내림차순으로 정렬하고 상위 10개를 선택한다
            List<CommonResponseDto> sortedList =  combinedList.stream()
                    .sorted(Comparator.comparing(CommonResponseDto::getMinSugar).reversed())
                    .limit(10)
                    .toList();
        }
        else if(sort == 1) {
            //get beverages
            beverages = cafeRepository.getMaxSugarBeverageAsc();
            //get desserts
            desserts = dessertRepository.findDessertListAsc();

            // 두 리스트를 합친다
            List<CommonResponseDto> combinedList = new ArrayList<>();
            combinedList.addAll(desserts);
            combinedList.addAll(beverages);

            // sugar 값에 따라 내림차순으로 정렬하고 상위 10개를 선택한다
            List<CommonResponseDto> sortedList =  combinedList.stream()
                    .sorted(Comparator.comparing(CommonResponseDto::getMinSugar))
                    .limit(10)
                    .toList();
        }
        else {
            throw new ParameterInvalidException("Parameter sort can be 0 or 1, but sort is " + sort);
        }
        return BeverageAndDessertListDto.builder()
                .beverages(beverages)
                .desserts(desserts)
                .build();

    }

}
