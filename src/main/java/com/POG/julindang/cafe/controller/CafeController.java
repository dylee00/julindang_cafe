package com.POG.julindang.cafe.controller;

import com.POG.julindang.cafe.dto.response.cafe.CafeLikeResponseDto;
import com.POG.julindang.cafe.dto.response.cafe.CafeResponseDto;
import com.POG.julindang.cafe.service.CafeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cafe")
@Tag(name = "cafe")
public class CafeController {
    private final CafeService cafeService;

    //카페 이름으로 검색
    @Operation(description = "카페이름 like 연산자")
    @GetMapping("/search")
    @Parameters({
            @Parameter(name="cafeName", description = "카페 이름")

    })
    public ResponseEntity<List<CafeResponseDto>> findByCafeName(@RequestParam(value = "cafeName") String cafeName){
        return ResponseEntity.ok(cafeService.findByCafeName(cafeName));
    }


    //즐겨찾기 + 나머지 리턴
    @Operation(description = "모든 카페 정보(isLike: 1은 좋아요) 리턴")
    @GetMapping
    public ResponseEntity<List<CafeLikeResponseDto>> findCafeNames(){
        return ResponseEntity.ok(cafeService.findCafeNames());
    }

    //즐겨찾기 한 카페 리턴
    @Operation(description = "즐겨찾기 한 카페 리턴")
    @GetMapping("/like")
    public ResponseEntity<List<CafeLikeResponseDto>> findCafeNamesByBookmark() {
        return ResponseEntity.ok(cafeService.findCafeNamesByBookmark());
    }
}
