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

    @Builder
    public BeverageNameResponseDto(Long id, String beverageName) {
        this.id = id;
        this.beverageName = beverageName;
    }

}
