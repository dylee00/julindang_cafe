package com.POG.julindang.cafe.dto.response.bookmark;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookmarkProductNameResponseDto {
    private Long id;

    @Builder
    public BookmarkProductNameResponseDto(Long id) {
        this.id = id;
    }
}
