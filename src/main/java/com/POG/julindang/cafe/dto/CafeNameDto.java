package com.POG.julindang.cafe.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CafeNameDto {
    private Long id;
    private String cafeName;

    @Builder
    public CafeNameDto(Long id, String cafeName) {
        this.id = id;
        this.cafeName = cafeName;
    }
}
