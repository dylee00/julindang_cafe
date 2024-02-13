package com.POG.julindang.cafe.dto;

import com.POG.julindang.cafe.domain.Topping;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ToppingSaveDto {
    private String beverageName;
    private String cafeName;
    private String toppingName;
    private Double sugar;
    private Double calorie;
    private Boolean deleted;
    private String manager;

    @Builder
    public ToppingSaveDto(String beverageName, String cafeName, String toppingName, Double sugar, Double calorie, Boolean deleted, String manager) {
        this.beverageName = beverageName;
        this.cafeName = cafeName;
        this.toppingName = toppingName;
        this.sugar = sugar;
        this.calorie = calorie;
        this.deleted = deleted;
        this.manager = manager;
    }

    public Topping toEntity(){
        return Topping.builder()
                .toppingName(toppingName)
                .sugar(sugar)
                .calorie(calorie)
                .cafeName(cafeName)
                .deleted(false)
                .manager(manager)
                .beverageName(beverageName)
                .build();
    }
}
