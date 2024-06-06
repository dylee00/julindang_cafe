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

    private String beverageName;

    private String size;

    private Double serve;

    private Double sugar;

    private Double calorie;

    private Boolean temperature;



    public BeverageDetailResponseDto(Cafe cafe) {
        this.cafeName = cafe.getCafeName();
        this.beverageName = cafe.getBeverageName();
        this.size = cafe.getSize();
        this.serve = cafe.getServe();
        this.sugar = cafe.getSugar();
        this.calorie = cafe.getCalorie();
        this.temperature = cafe.getTemperature();
    }
}
