package com.POG.julindang.cafe.dto;


import com.POG.julindang.cafe.domain.Topping;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ToppingDto {
    private Long id;
    private String toppingName;
    private Double sugar;
    private Double calorie;


    @Builder
    public ToppingDto(Long id, String toppingName, Double sugar, Double calorie) {
        this.id = id;
        this.toppingName = toppingName;
        this.sugar = sugar;
        this.calorie = calorie;
    }

    public ToppingDto(Topping topping) {
        this.id = topping.getId();
        this.toppingName = topping.getToppingName();
        this.sugar =topping.getSugar();
        this.calorie = topping.getCalorie();
    }


    public Topping toEntity(){
        return Topping.builder()
                .id(id)
                .toppingName(toppingName)
                .sugar(sugar)
                .calorie(calorie)
                .build();
    }
}
