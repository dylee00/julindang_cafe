package com.POG.julindang.cafe.dto.response.dessert;

import com.POG.julindang.cafe.domain.Dessert;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class DessertDetailResponseDto {
    private String cafeName;
    private String dessertName;
    private Double serve;
    private Double sugar;
    private Double calorie;

    public DessertDetailResponseDto(Dessert dessert){
        this.cafeName = dessert.getCafeName();
        this.dessertName = dessert.getDessertName();
        this.serve = dessert.getServe();
        this.sugar = dessert.getSugar();
        this.calorie = dessert.getCalorie();
    }
}
