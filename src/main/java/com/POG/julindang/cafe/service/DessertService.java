package com.POG.julindang.cafe.service;


import com.POG.julindang.cafe.domain.Dessert;
import com.POG.julindang.cafe.dto.response.common.CommonResponseDto;
import com.POG.julindang.cafe.dto.response.dessert.DessertFindResponseDto;
import com.POG.julindang.cafe.dto.response.dessert.DessertDetailResponseDto;
import com.POG.julindang.cafe.repository.DessertRepository;
import com.POG.julindang.cafe.util.JwtUtil;
import com.POG.julindang.cafe.vo.DessertNameVo;
import com.POG.julindang.common.exception.beverage.ParameterInvalidException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class DessertService {
    private final DessertRepository dessertRepository;

    public List<DessertFindResponseDto> findAll(Integer page){
        // 디저트 정보 전체 불러오기
        // 디저트 디테일 불러오기
        // 카페 이름에 따른 디저트 정보 불러오기
        // 디저트 이름에 따른 디저트 정보 불러오기
        Integer size = 10;
        Integer offset = page * size;
        return getDessertNameResponseDto(dessertRepository.findAll(size, offset, JwtUtil.getEmail()));
    }

    public List<DessertFindResponseDto> findByCafeName(String cafeName){
        return getDessertNameResponseDto(dessertRepository.findByCafeName(cafeName, JwtUtil.getEmail()));
    }
    public List<DessertFindResponseDto> findByDessertName(String dessertName){
        return getDessertNameResponseDto(dessertRepository.findByDessertName(dessertName, JwtUtil.getEmail()));
    }

    public List<DessertDetailResponseDto> findDessertDetails(String cafeName, String dessertName){
        List<Dessert> result = dessertRepository.findByCafeNameAndDessertName(cafeName, dessertName);
        return result.stream()
                .map(DessertDetailResponseDto::new)
                .collect(Collectors.toList());
    }

    private List<DessertFindResponseDto> getDessertNameResponseDto(List<DessertNameVo> find){
        List<DessertFindResponseDto> result = new ArrayList<>();
        for (DessertNameVo dessertNameVo : find) {
            result.add(DessertFindResponseDto.builder()
                    .dessertName(dessertNameVo.getDessertName())
                    .url(dessertNameVo.getUrl())
                    .cafeName(dessertNameVo.getCafeName())
                    .maxSugar(dessertNameVo.getMaxSugar())
                    .minSugar(dessertNameVo.getMinSugar())
                    .bookmarked(dessertNameVo.getBookmarked())
                    .build());
        }
        return result;
    }

    public List<CommonResponseDto> getDessertList(Long sort) {
        if(sort == 0)
            return dessertRepository.getDessertListDesc();
        else if(sort == 1)
            return dessertRepository.findDessertListAsc();
        else
            throw new ParameterInvalidException("Parameter sort can be 0 or 1, but sort: " + sort);
    }
}
