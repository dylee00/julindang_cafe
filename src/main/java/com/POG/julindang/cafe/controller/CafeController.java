package com.POG.julindang.cafe.controller;

import com.POG.julindang.cafe.dto.response.cafe.CafeLikeResponseDto;
import com.POG.julindang.cafe.dto.response.cafe.CafeResponseDto;
import com.POG.julindang.cafe.service.CafeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cafe")
@Tag(name = "cafe")
public class CafeController {
    private final CafeService cafeService;

    @Operation(description = "카페이름 like 연산자")
    @GetMapping("/search")
    public ResponseEntity<List<CafeResponseDto>> findByCafeName(@RequestParam(name = "query", required = false) String cafeName){
        return new ResponseEntity<>(cafeService.findByCafeName(cafeName), HttpStatus.OK);
    }


    @Operation(description = "모든 카페 정보(isLike: 1은 좋아요) 리턴")
    @GetMapping
    public ResponseEntity<List<CafeLikeResponseDto>> findCafeNames(){
        return new ResponseEntity<>(cafeService.findCafeNames(), HttpStatus.OK);
    }
}
