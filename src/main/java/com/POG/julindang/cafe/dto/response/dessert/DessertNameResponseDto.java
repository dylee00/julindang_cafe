package com.POG.julindang.cafe.dto.response.dessert;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class DessertNameResponseDto {
    private Long id;
    private String dessertName;

    @Builder
    public DessertNameResponseDto(Long id, String dessertName) {
        this.id = id;
        this.dessertName = dessertName;
    }
}
