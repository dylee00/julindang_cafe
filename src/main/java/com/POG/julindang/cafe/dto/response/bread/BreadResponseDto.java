package com.POG.julindang.cafe.dto.response.bread;

import com.POG.julindang.cafe.domain.Bread;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class BreadResponseDto {
    private String cafeName;
    private String breadName;
    private Double calorie;
    private Double serve;
    private Double sugar;

    @Builder
    public BreadResponseDto(String cafeName, String breadName, Double calorie, Double serve, Double sugar) {
        this.cafeName = cafeName;
        this.breadName = breadName;
        this.calorie = calorie;
        this.serve = serve;
        this.sugar = sugar;
    }

    public BreadResponseDto(Bread bread){
        this.breadName = bread.getBreadName();
        this.calorie=bread.getCalorie();
        this.serve=bread.getServe();
        this.sugar=bread.getSugar();
        this.cafeName=bread.getCafeName();
    }
}
