package com.POG.julindang.cafe.dto.response.bookmark;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FreeConsumeBookmarkResponseDto {
    private Long productId;
    private String name;
    private Long sugar;
    private Long calorie;
    private Integer type;
    private LocalDateTime createdAt;

}
