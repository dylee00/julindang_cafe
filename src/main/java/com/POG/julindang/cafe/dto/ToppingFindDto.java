package com.POG.julindang.cafe.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ToppingFindDto {
    private String cafeName;
    private String beverageName;

    @Builder
    public ToppingFindDto(String cafeName, String beverageName) {
        this.cafeName = cafeName;
        this.beverageName = beverageName;
    }
}
