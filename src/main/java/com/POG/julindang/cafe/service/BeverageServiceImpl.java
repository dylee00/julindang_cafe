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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class BeverageServiceImpl implements BeverageService {

    private final CafeRepository cafeRepository;

    //카페이름에 따른 음료 불러오기
    @Override
    @Transactional(readOnly = true)
    public List<BeverageFindResponseDto> findByCafeName(String cafeName){
        String cafeNameWithWildcards = "+" + cafeName + "*";
        return getBeverageFindResponseDto(cafeRepository.findByCafeName(cafeNameWithWildcards));
    }

    //음료 이름에 따른 음료들 불러오기
    @Override
    @Transactional(readOnly = true)
    public List<BeverageFindResponseDto> findByBeverageName(String beverageName){
        String beverageNameWithWildcards = "+" + beverageName + "*";
        return getBeverageFindResponseDto(cafeRepository.findByBeverageName(beverageNameWithWildcards));
    }

    private List<BeverageFindResponseDto> getBeverageFindResponseDto(List<BeverageNameVo> find){
        List<BeverageFindResponseDto> result = new ArrayList<>();
        for (BeverageNameVo beverageNameVo : find) {
            result.add(BeverageFindResponseDto.builder()
                    .beverageName(beverageNameVo.getBeverageName())
                    .cafeId(beverageNameVo.getCafeId())
                    .url(beverageNameVo.getUrl())
                    .maxSugar(beverageNameVo.getMaxSugar())
                    .minSugar(beverageNameVo.getMinSugar())
                    .cafeName(beverageNameVo.getCafeName())
                    .bookmarked(beverageNameVo.getBookmarked())
                    .build());
        }
        return result;
    }

    //음료 이름과 카페 이름으로 디테일 불러오기
    @Override
    @Transactional(readOnly = true)
    public List<BeverageDetailResponseDto> findBeverageDetails(String cafeName, String beverageName){
        List<Cafe> cafes = cafeRepository.findByCafeNameAndBeverageName(cafeName, beverageName);
        List<BeverageDetailResponseDto> dto = new ArrayList<>();

        for(Cafe cafe:cafes){
            dto.add(
                    BeverageDetailResponseDto.builder()
                            .cafeName(cafe.getCafeName())
                            .cafeId(cafe.getCafeId())
                            .beverageName(cafe.getBeverageName())
                            .size(cafe.getSize())
                            .serve(cafe.getServe())
                            .sugar(cafe.getSugar())
                            .calorie(cafe.getCalorie())
                            .temperature(cafe.getTemperature())
                            .build()
            );
        }

        return dto;
    }

//홈 화면의 음료/디저트 항목들
    @Override
    @Transactional(readOnly = true)
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
