package com.POG.julindang.cafe.dto;

import com.POG.julindang.cafe.domain.Topping;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdminToppingDto {
    private Long id;
    private String toppingName;
    private Double sugar;
    private Double calorie;
    private Boolean deleted;
    private String cafeName;
    private String beverageName;
    private String manager;
    @Builder
    public AdminToppingDto(String toppingName, Double sugar, Double calorie, Boolean deleted, String cafeName, String beverageName, String manager) {
        this.toppingName = toppingName;
        this.sugar = sugar;
        this.calorie = calorie;
        this.deleted = deleted;
        this.cafeName = cafeName;
        this.beverageName = beverageName;
        this.manager = manager;
    }




    public AdminToppingDto(Topping topping){
        this.id = topping.getId();
        this.toppingName = topping.getToppingName();
        this.sugar = topping.getSugar();
        this.calorie = topping.getCalorie();
        this.deleted = topping.getDeleted();
        this.cafeName = topping.getCafeName();
        this.beverageName = topping.getBeverageName();
        this.manager = topping.getManager();
    }

    public Topping toEntity(){
        return Topping.builder()
                .toppingName(toppingName)
                .sugar(sugar)
                .calorie(calorie)
                .deleted(deleted)
                .beverageName(beverageName)
                .cafeName(cafeName)
                .manager(manager)
                .build();
    }
}
