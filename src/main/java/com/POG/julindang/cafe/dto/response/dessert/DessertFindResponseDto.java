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
    private Double sugar;
    private String cafeName;
    private String url;
    private String bookmarked;
}
