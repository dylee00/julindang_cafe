package com.POG.julindang.cafe.dto.response.bookmark;


import com.POG.julindang.cafe.domain.CafeBookmark;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookmarkResponseDto {
    private String cafeName;
    private String productName;
}
