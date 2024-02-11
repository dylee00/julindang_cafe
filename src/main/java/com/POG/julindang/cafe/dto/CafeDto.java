package com.POG.julindang.cafe.dto;

import com.POG.julindang.cafe.domain.Cafe;
import lombok.*;

@Data
@NoArgsConstructor
public class CafeDto {
    Long id;
    String cafeName;

    String beverageName;

    String size;

    Double serve;

    Double sugar;

    Double calorie;

    Boolean temperature;
    Boolean deleted;
    @Builder
    public CafeDto(String cafeName, String beverageName, String size, Double serve, Double sugar, Double calorie, Boolean temperature){
        this.beverageName = beverageName;
        this.cafeName = cafeName;
        this.calorie = calorie;
        this.size = size;
        this.serve = serve;
        this.sugar = sugar;
        this.temperature =temperature;
    }

    public CafeDto(Cafe cafe) {
        this.cafeName = cafe.getCafeName();
        this.beverageName = cafe.getBeverageName();
        this.size = cafe.getSize();
        this.serve = cafe.getServe();
        this.sugar = cafe.getSugar();
        this.calorie = cafe.getCalorie();
        this.temperature = cafe.getTemperature();
        this.id = cafe.getId();
        this.deleted = cafe.getDeleted();
    }


    public Cafe toEntity(){
        return Cafe.builder()
                .sugar(sugar)
                .temperature(temperature)
                .calorie(calorie)
                .serve(serve)
                .size(size)
                .beverageName(beverageName)
                .cafeName(cafeName).build();
    }
}

