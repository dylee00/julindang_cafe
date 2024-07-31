package com.POG.julindang.cafe.dto.response.dessert;

import com.POG.julindang.cafe.domain.Dessert;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DessertDetailResponseDto {
    private String cafeName;
    private Long dessertId;
    private String dessertName;
    private Double serve;
    private Double sugar;
    private Double calorie;
    private String url;
    private String bookmarked;

}
