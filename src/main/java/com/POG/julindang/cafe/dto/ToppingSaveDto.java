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
    private String toppingName;
    private Double sugar;
    private Double calorie;

    @Builder
    public ToppingSaveDto(String toppingName, Double sugar, Double calorie) {
        this.toppingName = toppingName;
        this.sugar = sugar;
        this.calorie = calorie;
    }

    public Topping toEntity(){
        return Topping.builder()
                .toppingName(toppingName)
                .sugar(sugar)
                .calorie(calorie)
                .build();
    }
}
