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
    private String beverageName;

    @Builder
    public BookMarkSaveRequestDto(String userEmail, String cafeName, String beverageName) {
        this.userEmail = userEmail;
        this.cafeName = cafeName;
        this.beverageName = beverageName;
    }

    public BookMarkSaveRequestDto(Bookmark bookmark){
        this.userEmail = bookmark.getUserEmail();
        this.cafeName = bookmark.getCafeName();
        this.beverageName = bookmark.getBeverageName();
    }
    public Bookmark toEntity(){
        return Bookmark.builder()
                .beverageName(beverageName)
                .cafeName(cafeName)
                .userEmail(userEmail)
                .build();
    }
}
