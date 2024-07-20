package com.POG.julindang.cafe.dto.response.bookmark;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeverageBookmarkResponseDto {
    private List<CafeBeverageBookmarkResponseDto> cafeBeverages;
    private List<FreeConsumeBookmarkResponseDto> freeConsumeBeverages;
}
