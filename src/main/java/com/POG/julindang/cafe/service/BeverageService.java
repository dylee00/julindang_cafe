package com.POG.julindang.cafe.service;

import com.POG.julindang.cafe.domain.Cafe;
import com.POG.julindang.cafe.dto.response.beverage.BeverageDetailResponseDto;
import com.POG.julindang.cafe.dto.response.beverage.BeverageFindResponseDto;
import com.POG.julindang.cafe.dto.response.common.CommonResponseDto;
import com.POG.julindang.cafe.repository.CafeRepository;
import com.POG.julindang.cafe.util.JwtUtil;
import com.POG.julindang.cafe.vo.BeverageNameVo;
import com.POG.julindang.common.exception.beverage.ParameterInvalidException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BeverageService {

    private final CafeRepository cafeRepository;

    public List<BeverageFindResponseDto> findAll(Integer page){
        // 음료 정보 전체 불러오기
        // 음료 디테일 불러오기
        // 카페 이름에 따른 음료 정보 불러오기
        // 음료 이름에 따른 음료 정보 불러오기
        Integer size = 10;
        Integer offset = page * size;
        return getBeverageFindResponseDto(cafeRepository.findAllBeverageNames(size, offset, JwtUtil.getEmail()));
    }

    public List<BeverageFindResponseDto> findByCafeName(String cafeName){
        return getBeverageFindResponseDto(cafeRepository.findByCafeName(cafeName, JwtUtil.getEmail()));
    }

    public List<BeverageFindResponseDto> findByBeverageName(String beverageName){
        return getBeverageFindResponseDto(cafeRepository.findByBeverageName(beverageName, JwtUtil.getEmail()));
    }
    public List<BeverageDetailResponseDto> findBeverageDetails(String cafeName, String beverageName){
        List<Cafe> result = cafeRepository.findByCafeNameAndBeverageName(cafeName, beverageName);

        return result.stream()
                .map(BeverageDetailResponseDto::new)
                .collect(Collectors.toList());
    }
    private List<BeverageFindResponseDto> getBeverageFindResponseDto(List<BeverageNameVo> find){
        List<BeverageFindResponseDto> result = new ArrayList<>();
        for (BeverageNameVo beverageNameVo : find) {
            result.add(BeverageFindResponseDto.builder()
                    .beverageName(beverageNameVo.getBeverageName())
                    .url(beverageNameVo.getUrl())
                    .maxSugar(beverageNameVo.getMaxSugar())
                    .minSugar(beverageNameVo.getMinSugar())
                    .cafeName(beverageNameVo.getCafeName())
                    .bookmarked(beverageNameVo.getBookmarked())
                    .build());
        }
        return result;
    }

    public List<CommonResponseDto> getBeverageList(Long sort) {
        List<CommonResponseDto> beverages = new ArrayList<>();

        // response from repository with max 10 sugar
        if(sort == 0)
            beverages = cafeRepository.getMaxSugarBeverageDesc();
        else if(sort == 1)
            beverages = cafeRepository.getMaxSugarBeverageAsc();
        else throw new ParameterInvalidException("Parameter sort can be 0 or 1 but sort: " + sort);

        return beverages;
    }
}
