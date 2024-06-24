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

@Service
@Slf4j
@RequiredArgsConstructor
public class DessertServiceImpl implements DessertService {
    private final DessertRepository dessertRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DessertFindResponseDto> findByCafeName(String cafeName){
        return getDessertNameResponseDto(dessertRepository.findByCafeName(cafeName, JwtUtil.getEmail()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<DessertDetailResponseDto> findDessertDetails(String cafeName, String dessertName){
        List<Dessert> desserts= dessertRepository.findByCafeNameAndDessertName(cafeName, dessertName);
        List<DessertDetailResponseDto> dto = new ArrayList<>();

        for(Dessert dessert:desserts){
            dto.add(
                    DessertDetailResponseDto.builder()
                            .cafeName(dessert.getCafeName())
                            .dessertId(dessert.getDessertId())
                            .dessertName(dessert.getDessertName())
                            .calorie(dessert.getCalorie())
                            .serve(dessert.getServe())
                            .sugar(dessert.getSugar())
                            .build()
            );
        }
        return dto;
    }


    @Override
    @Transactional
    public List<DessertFindResponseDto> findAll(){
        return getDessertNameResponseDto(dessertRepository.findAll(JwtUtil.getEmail()));
    }

    @Override
    @Transactional
    public List<DessertFindResponseDto> findByDessertName(String dessertName){
        return getDessertNameResponseDto(dessertRepository.findByDessertName(dessertName, JwtUtil.getEmail()));
    }

    private List<DessertFindResponseDto> getDessertNameResponseDto(List<DessertNameVo> find){
        List<DessertFindResponseDto> result = new ArrayList<>();
        for (DessertNameVo dessertNameVo : find) {
            result.add(DessertFindResponseDto.builder()
                    .dessertName(dessertNameVo.getDessertName())
                    .dessertId(dessertNameVo.getDessertId())
                    .url(dessertNameVo.getUrl())
                    .cafeName(dessertNameVo.getCafeName())
                    .maxSugar(dessertNameVo.getMaxSugar())
                    .minSugar(dessertNameVo.getMinSugar())
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
}
