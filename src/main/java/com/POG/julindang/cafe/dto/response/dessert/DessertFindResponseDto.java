package com.POG.julindang.cafe.dto.response.dessert;


import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DessertFindResponseDto {
    private String dessertName;
    private Long dessertId;
    private Double maxSugar;
    private Double minSugar;
    private String cafeName;
    private String url;
    private Integer bookmarked;
}
