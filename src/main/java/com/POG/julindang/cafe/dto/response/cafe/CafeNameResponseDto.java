package com.POG.julindang.cafe.dto.response.cafe;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CafeNameResponseDto {
    private Long id;
    private String cafeName;

    @Builder
    public CafeNameResponseDto(Long id, String cafeName) {
        this.id = id;
        this.cafeName = cafeName;
    }
}
