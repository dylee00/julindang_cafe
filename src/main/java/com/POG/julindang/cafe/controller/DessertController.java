package com.POG.julindang.cafe.controller;


import com.POG.julindang.cafe.dto.response.dessert.DessertDetailResponseDto;
import com.POG.julindang.cafe.dto.response.dessert.DessertFindResponseDto;
import com.POG.julindang.cafe.service.DessertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cafe/dessert")
@Tag(name = "dessert")
public class DessertController {
    private final DessertService dessertService;

    @Operation(description = "디저트 정보 전체 불러오기")
    @GetMapping
    @Parameters({
            @Parameter(name="page", description = "페이지 수 0 부터 시작")
    })
    public ResponseEntity<List<DessertFindResponseDto>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page){
        return new ResponseEntity<>(dessertService.findAll(page), HttpStatus.OK);
    }

    @Operation(description = "디저트 이름과 카페 이름으로 디테일 불러오기")
    @GetMapping("/details")
    @Parameters({
            @Parameter(name="cafeName", description = "카페 이름"),
            @Parameter(name="beverageName", description = "디저트 이름")
    })
    public ResponseEntity<List<DessertDetailResponseDto>> findByCafeNameAndBeverageName(@RequestParam(value = "cafeName") String cafeName,
                                                                                        @RequestParam(value = "beverageName") String beverageName){
        return new ResponseEntity<>(dessertService.findDessertDetails(cafeName, beverageName), HttpStatus.OK);
    }

    @Operation(description = "카페 이름에 따른 디저트들 불러오기")
    @GetMapping("/by-cafe-name")
    @Parameters({
            @Parameter(name="cafeName", description = "카페 이름"),
            @Parameter(name="userEmail", description = "유저 이메일 (즐겨찾기 조회용)")
    })
    public ResponseEntity<List<DessertFindResponseDto>> findByCafeName(@RequestParam(value = "cafeName") String cafeName){
        return new ResponseEntity<>(dessertService.findByCafeName(cafeName), HttpStatus.OK);
    }

    @Operation(description = "디저트 이름에 따른 디저트들 불러오기")
    @GetMapping("/by-dessert-name")
    @Parameters({
            @Parameter(name="beverageName", description = "디저트 이름"),
            @Parameter(name="userEmail", description = "유저 이메일 (즐겨찾기 조회용)")
    })
    public ResponseEntity<List<DessertFindResponseDto>> findByBeverageName(@RequestParam(value = "beverageName") String beverageName){
        return new ResponseEntity<>(dessertService.findByDessertName(beverageName), HttpStatus.OK);
    }
}
