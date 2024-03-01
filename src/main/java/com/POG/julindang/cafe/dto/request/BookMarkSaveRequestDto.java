package com.POG.julindang.cafe.dto.request;


import com.POG.julindang.cafe.domain.Bookmark;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookMarkSaveRequestDto {
    private String userEmail;
    private String cafeName;
    private String productName;

    @Builder
    public BookMarkSaveRequestDto(String userEmail, String cafeName, String productName) {
        this.userEmail = userEmail;
        this.cafeName = cafeName;
        this.productName = productName;
    }

    public BookMarkSaveRequestDto(Bookmark bookmark){
        this.userEmail = bookmark.getUserEmail();
        this.cafeName = bookmark.getCafeName();
        this.productName = bookmark.getProductName();
    }
    public Bookmark toEntity(){
        return Bookmark.builder()
                .productName(productName)
                .cafeName(cafeName)
                .userEmail(userEmail)
                .build();
    }
}
