package com.POG.julindang.cafe.dto.response.beverage;

import com.POG.julindang.cafe.domain.Cafe;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeverageDetailResponseDto {
    private String cafeName;

    private Long cafeId;

    private String beverageName;

    private String size;

    private Double serve;

    private Double sugar;

    private Double calorie;

    private Boolean temperature;

}
