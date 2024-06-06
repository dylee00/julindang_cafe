package com.POG.julindang.cafe.dto.request;


import com.POG.julindang.cafe.domain.CafeBookmark;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class BookMarkSaveRequestDto {
    @Schema(example = "ex@ex.com")
    private String userEmail;
    @Schema( example = "스타벅스")
    private String cafeName;
    @Schema(description = "[ 0 : 카페 브렌드 즐겨찾기(이때는 productName 불필요), 1 : 음료 즐겨찾기 (모든 파라미터 필요), 2 : 디저트 즐겨찾기 (모든 파라미터 필요) ]", example = "0")
    private Integer type;
    @Schema(description = "type 이 1 또는 2일때만 필요", example = "아이스 아메리카노")
    private String productName;
}
