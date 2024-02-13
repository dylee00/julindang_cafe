package com.POG.julindang.cafe.dto;


import com.POG.julindang.cafe.domain.Cafe;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdminCafeDto {
    private Long id;
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
    public AdminCafeDto(Long id, String cafeName, String beverageName, String size, Double serve, Double sugar, Double calorie, Boolean temperature, Boolean deleted, String manager) {
        this.id = id;
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




    public AdminCafeDto(Cafe cafe) {
        this.cafeName = cafe.getCafeName();
        this.beverageName = cafe.getBeverageName();
        this.size = cafe.getSize();
        this.serve = cafe.getServe();
        this.sugar = cafe.getSugar();
        this.calorie = cafe.getCalorie();
        this.temperature = cafe.getTemperature();
        this.id = cafe.getId();
        this.deleted = cafe.getDeleted();
        this.manager = cafe.getManager();
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
                .manager(manager)
                .build();


    }
}
