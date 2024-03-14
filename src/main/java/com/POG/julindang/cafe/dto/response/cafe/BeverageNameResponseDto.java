package com.POG.julindang.cafe.dto.response.cafe;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BeverageNameResponseDto {
    private Long id;
    private String beverageName;
    private Double maxSugar;
    private Double minSugar;
    @Builder
    public BeverageNameResponseDto(Long id, String beverageName, Double maxSugar, Double minSugar) {
        this.id = id;
        this.beverageName = beverageName;
        this.maxSugar = maxSugar;
        this.minSugar = minSugar;
    }
}
