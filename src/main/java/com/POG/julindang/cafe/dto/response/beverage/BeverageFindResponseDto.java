package com.POG.julindang.cafe.dto.response.beverage;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeverageFindResponseDto {
    private String beverageName;
    private Double maxSugar;
    private Double minSugar;
    private String cafeName;
    private String url;
    private Integer bookmarked;
}
