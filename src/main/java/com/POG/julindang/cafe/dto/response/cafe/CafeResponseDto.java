package com.POG.julindang.cafe.dto.response.cafe;

import com.POG.julindang.cafe.domain.Cafe;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class CafeResponseDto {
    private String cafeName;

    private String beverageName;

    private String size;

    private Double serve;

    private Double sugar;

    private Double calorie;

    private Boolean temperature;

    @Builder
    public CafeResponseDto(String cafeName, String beverageName, String size, Double serve, Double sugar, Double calorie, Boolean temperature) {
        this.cafeName = cafeName;
        this.beverageName = beverageName;
        this.size = size;
        this.serve = serve;
        this.sugar = sugar;
        this.calorie = calorie;
        this.temperature = temperature;
    }




    public CafeResponseDto(Cafe cafe) {
        this.cafeName = cafe.getCafeName();
        this.beverageName = cafe.getBeverageName();
        this.size = cafe.getSize();
        this.serve = cafe.getServe();
        this.sugar = cafe.getSugar();
        this.calorie = cafe.getCalorie();
        this.temperature = cafe.getTemperature();
    }


    public Cafe toEntity(){
        return Cafe.builder()
                .sugar(sugar)
                .temperature(temperature)
                .calorie(calorie)
                .serve(serve)
                .size(size)
                .beverageName(beverageName)
                .cafeName(cafeName)
                .build();


    }
}

