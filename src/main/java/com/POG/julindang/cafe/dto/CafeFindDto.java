package com.POG.julindang.cafe.dto;

import lombok.*;

@Data
@NoArgsConstructor
public class CafeFindDto {
    private String cafeName;
    private String beverageName;
    private String size;
    @Builder
    public CafeFindDto(String beverageName, String cafeName ,String size){
        this.cafeName = cafeName;
        this.beverageName = beverageName;
        this.size = size;
    }
}
