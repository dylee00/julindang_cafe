package com.POG.julindang.cafe.dto.response.beverage;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeverageFindResponseDto {
    private String beverageName;
    private Long cafeId;
    private Double minSugar;
    private Double maxSugar;
    private String cafeName;
    private String url;
    private String bookmarked;
}
