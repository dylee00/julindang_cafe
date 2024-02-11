package com.POG.julindang.cafe.dto;

import lombok.*;

@Data
@NoArgsConstructor
public class CafeFindDto {
    private String cafeName;
    private String beverageName;

    @Builder
    public CafeFindDto(String beverageName, String cafeName){
        this.cafeName = cafeName;
        this.beverageName = beverageName;
    }
}
