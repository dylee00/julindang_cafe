package com.POG.julindang.cafe.dto.response.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponseDto {
    private Long id;
    private String name;
    private String img;
    private Long maxSugar;
    private Long minSugar;
    private Long isLiked;
}
