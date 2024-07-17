package com.POG.julindang.cafe.service;

import com.POG.julindang.cafe.dto.response.cafe.CafeLikeResponseDto;
import com.POG.julindang.cafe.dto.response.cafe.CafeResponseDto;
import com.POG.julindang.cafe.dto.response.common.CommonResponseDto;

import java.util.List;

public interface CafeService {
    public List<CafeLikeResponseDto> findCafeNames();

    public List<CafeResponseDto> findByCafeName(String cafeName);

    public List<CafeLikeResponseDto> findCafeNamesByBookmark();

    public List<CafeLikeResponseDto> findCafeNamesByNotBookmark();

    public List<CommonResponseDto> getBeveragesAndDesserts(Long sort);

}
