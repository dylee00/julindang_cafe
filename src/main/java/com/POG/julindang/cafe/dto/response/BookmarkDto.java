package com.POG.julindang.cafe.dto.response;


import com.POG.julindang.cafe.domain.Bookmark;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookmarkDto {
    private String userEmail;
    private String cafeName;
    private String beverageName;

    @Builder
    public BookmarkDto(String userEmail, String cafeName, String beverageName) {
        this.userEmail = userEmail;
        this.cafeName = cafeName;
        this.beverageName = beverageName;
    }

    public BookmarkDto(Bookmark bookmark){
        this.userEmail = bookmark.getUserEmail();
        this.beverageName = bookmark.getBeverageName();
        this.cafeName = bookmark.getCafeName();
    }

    public Bookmark toEntity(){
        return Bookmark.builder()
                .beverageName(beverageName)
                .cafeName(cafeName)
                .userEmail(userEmail)
                .build();
    }
}
