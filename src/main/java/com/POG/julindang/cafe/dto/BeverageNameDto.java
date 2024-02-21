package com.POG.julindang.cafe.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BeverageNameDto {
    private Long id;
    private String beverageName;

    @Builder
    public BeverageNameDto(Long id, String beverageName) {
        this.id = id;
        this.beverageName = beverageName;
    }

}
