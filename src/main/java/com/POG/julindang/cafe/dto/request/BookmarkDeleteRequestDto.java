package com.POG.julindang.cafe.dto.request;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookmarkDeleteRequestDto {
    private Integer type;
    private String cafeName;
    private Long productId;
}
