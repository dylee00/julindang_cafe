package com.POG.julindang.cafe.dto.response.bookmark;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DessertBookmarkResponseDto {
    private List<CafeDessertBookmarkResponseDto> cafeDesserts;
    private List<FreeConsumeResponseDto> freeConsumeDesserts;
}
