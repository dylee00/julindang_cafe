package com.POG.julindang.cafe.service;


import com.POG.julindang.cafe.domain.Dessert;
import com.POG.julindang.cafe.dto.response.common.CommonResponseDto;
import com.POG.julindang.cafe.dto.response.dessert.DessertFindResponseDto;
import com.POG.julindang.cafe.dto.response.dessert.DessertDetailResponseDto;
import com.POG.julindang.cafe.repository.DessertRepository;
import com.POG.julindang.cafe.util.JwtUtil;
import com.POG.julindang.cafe.vo.DessertDetailVo;
import com.POG.julindang.cafe.vo.DessertNameVo;
import com.POG.julindang.common.exception.beverage.ParameterInvalidException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DessertServiceImpl implements DessertService {
    private final DessertRepository dessertRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DessertFindResponseDto> findByCafeName(String cafeName){
        Long memberId = JwtUtil.getMemberId();
        String cafeNameWithWildcards = "+" + cafeName + "*";
        return getDessertNameResponseDto(dessertRepository.findByCafeName(cafeNameWithWildcards,memberId));
    }

    //dessertDetailVo 재활용하기
    @Override
    @Transactional(readOnly = true)
    public List<DessertDetailVo> findDessertDetails(String cafeName, String dessertName){
        return dessertRepository.findByCafeNameAndDessertName(cafeName, dessertName, JwtUtil.getMemberId());
    }


    @Override
    @Transactional(readOnly = true)
    public List<DessertFindResponseDto> findAll(){
        return getDessertNameResponseDto(dessertRepository.findAll(JwtUtil.getMemberId()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<DessertFindResponseDto> findByDessertName(String dessertName){
        Long memberId = JwtUtil.getMemberId();
//        String dessertNameWithWildcards = "+" + dessertName + "*";

        // 검색어를 BOOLEAN MODE에 적합하게 변환
        String[] keywords = dessertName.split(" ");
        StringBuilder dessertNameWithWildcards = new StringBuilder();
        for (String keyword : keywords) {
            dessertNameWithWildcards.append("+").append(keyword).append(" ");
        }
        dessertNameWithWildcards.append("*");

        return getDessertNameResponseDto(dessertRepository.findByDessertName(dessertNameWithWildcards.toString().trim(),memberId));
    }

    private List<DessertFindResponseDto> getDessertNameResponseDto(List<DessertNameVo> find){
        List<DessertFindResponseDto> result = new ArrayList<>();
        for (DessertNameVo dessertNameVo : find) {
            result.add(DessertFindResponseDto.builder()
                    .dessertName(dessertNameVo.getDessertName())
                    .dessertId(dessertNameVo.getDessertId())
                    .url(dessertNameVo.getUrl())
                    .cafeName(dessertNameVo.getCafeName())
                    .sugar(dessertNameVo.getSugar())
                    .bookmarked(dessertNameVo.getBookmarked())
                    .build());
        }
        return result;
    }


    @Override
    @Transactional
    public List<CommonResponseDto> getDessertList(Long sort) {
        if(sort == 0)
            return dessertRepository.getDessertListDesc();
        else if(sort == 1)
            return dessertRepository.findDessertListAsc();
        else
            throw new ParameterInvalidException("Parameter sort can be 0 or 1, but sort: " + sort);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DessertDetailVo> getDetails(Long dessertId) {
        return dessertRepository.getDessertDetails(dessertId,JwtUtil.getMemberId());
    }
}
