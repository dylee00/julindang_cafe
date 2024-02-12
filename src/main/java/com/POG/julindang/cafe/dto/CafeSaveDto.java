package com.POG.julindang.cafe.dto;

import com.POG.julindang.cafe.domain.Cafe;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CafeSaveDto {
    private String cafeName;
    private String beverageName;
    private String size;
    private Double serve;
    private Double sugar;
    private Double calorie;
    private Boolean temperature;
    private Boolean deleted;
    private String manager;

    @Builder
    public CafeSaveDto(String cafeName, String beverageName, String size, Double serve, Double sugar, Double calorie, Boolean temperature, Boolean deleted, String manager) {
        this.cafeName = cafeName;
        this.beverageName = beverageName;
        this.size = size;
        this.serve = serve;
        this.sugar = sugar;
        this.calorie = calorie;
        this.temperature = temperature;
        this.deleted = deleted;
        this.manager = manager;
    }

    public Cafe toEntity(){
        return Cafe.builder()
                .beverageName(beverageName)
                .cafeName(cafeName)
                .calorie(calorie)
                .deleted(false)
                .sugar(sugar)
                .temperature(temperature)
                .serve(serve)
                .size(size)
//                .manager(manager)
                .build();
    }
}
