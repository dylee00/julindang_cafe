package com.POG.julindang.cafe.dto.response.bookmark;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CafeDessertBookmarkResponseDto {
    String dessertName;
    String cafeName;
    LocalDateTime createdAt;
    Double calorie;
    Double sugar;
    Long dessertId;
}
