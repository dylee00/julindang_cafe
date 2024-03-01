package com.POG.julindang.cafe.dto.response.bookmark;


import com.POG.julindang.cafe.domain.Bookmark;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookmarkResponseDto {
    private String userEmail;
    private String cafeName;
    private String productName;

    @Builder
    public BookmarkResponseDto(String userEmail, String cafeName, String productName) {
        this.userEmail = userEmail;
        this.cafeName = cafeName;
        this.productName = productName;
    }

    public BookmarkResponseDto(Bookmark bookmark){
        this.userEmail = bookmark.getUserEmail();
        this.productName = bookmark.getProductName();
        this.cafeName = bookmark.getCafeName();
    }

    public Bookmark toEntity(){
        return Bookmark.builder()
                .productName(productName)
                .cafeName(cafeName)
                .userEmail(userEmail)
                .build();
    }
}
