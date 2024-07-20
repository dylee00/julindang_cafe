package com.POG.julindang.cafe.service;

import com.POG.julindang.cafe.dto.response.common.CommonResponseDto;
import com.POG.julindang.cafe.dto.response.dessert.DessertDetailResponseDto;
import com.POG.julindang.cafe.dto.response.dessert.DessertFindResponseDto;
import com.POG.julindang.cafe.vo.DessertDetailVo;
import com.POG.julindang.cafe.vo.DessertNameVo;

import java.util.List;

public interface DessertService {
    public List<DessertFindResponseDto> findAll();

    public List<DessertFindResponseDto> findByCafeName(String cafeName);

    public List<DessertFindResponseDto> findByDessertName(String dessertName);

    public List<DessertDetailResponseDto> findDessertDetails(String cafeName, String dessertName);

    public List<CommonResponseDto> getDessertList(Long sort);

    public List<DessertDetailVo> getDetails(Long dessertId);


}
