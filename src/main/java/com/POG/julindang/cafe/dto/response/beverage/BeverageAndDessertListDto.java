package com.POG.julindang.cafe.dto.response.beverage;

import com.POG.julindang.cafe.dto.response.common.CommonResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class BeverageAndDessertListDto {
    private List<CommonResponseDto> beverages;
    private List<CommonResponseDto> desserts;
}
