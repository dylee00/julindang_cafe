package com.POG.julindang.cafe.service;

import com.POG.julindang.cafe.dto.response.beverage.BeverageDetailResponseDto;
import com.POG.julindang.cafe.dto.response.beverage.BeverageFindResponseDto;
import com.POG.julindang.cafe.dto.response.common.CommonResponseDto;
import com.POG.julindang.cafe.vo.BeverageDetailVo;

import java.util.List;

public interface BeverageService {
    public List<BeverageFindResponseDto> findByCafeName(String cafeName);

    public List<BeverageFindResponseDto> findByBeverageName(String beverageName);
    public List<BeverageDetailVo> findBeverageDetails(String cafeName, String beverageName);
    public List<CommonResponseDto> getBeverageList(Long sort);
    public List<BeverageDetailVo> getDetails(Long cafeId);
}
