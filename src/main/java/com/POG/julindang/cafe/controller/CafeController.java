package com.POG.julindang.cafe.controller;


import com.POG.julindang.cafe.dto.response.cafe.CafeFindResponseDto;
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


    @Operation(summary = "카페 이름을 통해 탐색 카페 탐색",
            description = "카페 이름과 일치하는 카페 정보 로드")
    @GetMapping("/by-cafe-name")
    public ResponseEntity<CafeFindResponseDto> findByCafeName(@RequestParam(name = "cafeName", required = false) String cafeName){
        return new ResponseEntity<>(cafeService.findByCafeName(cafeName), HttpStatus.OK);
    }


    @Operation(summary = "카페 이름 리턴",
            description = "모든 카페 정보 리턴")
    @GetMapping
    public ResponseEntity<List<CafeFindResponseDto>> findCafeNames(){
        return new ResponseEntity<>(cafeService.findCafeNames(), HttpStatus.OK);
    }


}
