package com.POG.julindang.cafe.dto.response.bread;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class BreadNameResponseDto {
    private Long id;
    private String breadName;

    @Builder
    public BreadNameResponseDto(Long id, String breadName) {
        this.id = id;
        this.breadName = breadName;
    }
}
