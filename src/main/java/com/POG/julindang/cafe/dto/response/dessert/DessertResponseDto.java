package com.POG.julindang.cafe.dto.response.dessert;

import com.POG.julindang.cafe.domain.Dessert;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class DessertResponseDto {
    private String cafeName;
    private String dessertName;
    private Double calorie;
    private Double serve;
    private Double sugar;

    @Builder
    public DessertResponseDto(String cafeName, String dessertName, Double calorie, Double serve, Double sugar) {
        this.cafeName = cafeName;
        this.dessertName = dessertName;
        this.calorie = calorie;
        this.serve = serve;
        this.sugar = sugar;
    }

    public DessertResponseDto(Dessert dessert){
        this.dessertName = dessert.getDessertName();
        this.calorie=dessert.getCalorie();
        this.serve=dessert.getServe();
        this.sugar=dessert.getSugar();
        this.cafeName=dessert.getCafeName();
    }
}
