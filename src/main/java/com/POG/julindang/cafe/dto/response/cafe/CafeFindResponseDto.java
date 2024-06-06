package com.POG.julindang.cafe.dto.response.cafe;



import com.POG.julindang.cafe.domain.CafeImage;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CafeFindResponseDto {
    private String cafeName;
    private String url;

    public CafeFindResponseDto(CafeImage cafeImage){
        this.cafeName = cafeImage.getCafeName();
        this.url = cafeImage.getUrl();
    }
}
