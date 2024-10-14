package com.POG.julindang.cafe.service;

import com.POG.julindang.cafe.domain.Cafe;
import com.POG.julindang.cafe.dto.response.beverage.BeverageDetailResponseDto;
import com.POG.julindang.cafe.dto.response.beverage.BeverageFindResponseDto;
import com.POG.julindang.cafe.dto.response.common.CommonResponseDto;
import com.POG.julindang.cafe.repository.CafeRepository;
import com.POG.julindang.cafe.util.JwtUtil;
import com.POG.julindang.cafe.vo.BeverageDetailVo;
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
        Long memberId = JwtUtil.getMemberId();
        String cafeNameWithWildcards = "+" + cafeName + "*";
        return getBeverageFindResponseDto(cafeRepository.findByCafeName(cafeNameWithWildcards, memberId));
    }

    //음료 이름에 따른 음료들 불러오기
    @Override
    @Transactional(readOnly = true)
    public List<BeverageFindResponseDto> findByBeverageName(String beverageName) {
        Long memberId = JwtUtil.getMemberId();

        // 검색어를 BOOLEAN MODE에 적합하게 변환
        String[] keywords = beverageName.split(" ");
        StringBuilder beverageNameWithWildcards = new StringBuilder();
        for (String keyword : keywords) {
            beverageNameWithWildcards.append("+").append(keyword).append(" ");
        }
        beverageNameWithWildcards.append("*");

        // 변환된 검색어로 쿼리 실행
        return getBeverageFindResponseDto(cafeRepository.findByBeverageName(beverageNameWithWildcards.toString().trim(), memberId));
    }


    private List<BeverageFindResponseDto> getBeverageFindResponseDto(List<BeverageNameVo> find){
        List<BeverageFindResponseDto> result = new ArrayList<>();
        for (BeverageNameVo beverageNameVo : find) {
            result.add(BeverageFindResponseDto.builder()
                    .beverageName(beverageNameVo.getBeverageName())
                    .cafeId(beverageNameVo.getCafeId())
                    .url(beverageNameVo.getUrl())
                    .minSugar(beverageNameVo.getMinSugar())
                    .maxSugar(beverageNameVo.getMaxSugar())
                    .cafeName(beverageNameVo.getCafeName())
                    .bookmarked(beverageNameVo.getBookmarked())
                    .build());
        }
        return result;
    }

    //음료 이름과 카페 이름으로 디테일 불러오기
    //BeverageDetailVo 재활용하기
    @Override
    @Transactional(readOnly = true)
    public List<BeverageDetailVo> findBeverageDetails(String cafeName, String beverageName){
        return cafeRepository.findByCafeNameAndBeverageName(cafeName, beverageName, JwtUtil.getMemberId());
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

    @Override
    @Transactional(readOnly = true)
    public List<BeverageDetailVo> getDetails(Long cafeId) {
        return cafeRepository.getBeverageDetails(cafeId,JwtUtil.getMemberId());
    }
}
