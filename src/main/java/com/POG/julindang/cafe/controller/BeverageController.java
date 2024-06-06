package com.POG.julindang.cafe.controller;

import com.POG.julindang.cafe.dto.response.beverage.BeverageDetailResponseDto;
import com.POG.julindang.cafe.dto.response.beverage.BeverageFindResponseDto;
import com.POG.julindang.cafe.service.BeverageService;
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
@RequestMapping("/cafe/beverage")
@Tag(name = "beverage")
public class BeverageController {
    private final BeverageService beverageService;

    @Operation(summary = "음료 정보 전체 불러오기",
            description = "음료 정보 전체 불러오기")
    @GetMapping
    @Parameters({
            @Parameter(name="page", description = "페이지 수 0 부터 시작"),
            @Parameter(name="userEmail", description = "유저 이메일 (즐겨찾기 조회용)")
    })
    public ResponseEntity<List<BeverageFindResponseDto>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                 @RequestParam("userEmail") String userEmail){
        return new ResponseEntity<>(beverageService.findAll(page, userEmail), HttpStatus.OK);
    }

    @Operation(summary = "음료 디테일 불러오기",
            description = "음료 이름과 카페 이름으로 디테일 불러오기")
    @GetMapping("/details")
    @Parameters({
            @Parameter(name="cafeName", description = "카페 이름"),
            @Parameter(name="beverageName", description = "음료 이름")

    })
    public ResponseEntity<List<BeverageDetailResponseDto>> findByCafeNameAndBeverageName(@RequestParam(value = "cafeName") String cafeName,
                                                                                         @RequestParam(value = "beverageName") String beverageName){
        return new ResponseEntity<>(beverageService.findBeverageDetails(cafeName, beverageName), HttpStatus.OK);
    }

    @Operation(summary = "카페 이름에 따른 음료들 불러오기",
            description = "카페 이름에 따른 음료들 불러오기")
    @GetMapping("/by-cafe-name")
    @Parameters({
            @Parameter(name="cafeName", description = "카페 이름"),
            @Parameter(name="userEmail", description = "유저 이메일 (즐겨찾기 조회용)")
    })
    public ResponseEntity<List<BeverageFindResponseDto>> findByCafeName(@RequestParam(value = "cafeName") String cafeName,
                                                                        @RequestParam("userEmail") String userEmail){
        return new ResponseEntity<>(beverageService.findByCafeName(cafeName, userEmail), HttpStatus.OK);
    }

    @Operation(summary = "음료 이름에 따른 음료들 불러오기",
            description = "음료 이름에 따른 음료들 불러오기")
    @GetMapping("/by-beverage-name")
    @Parameters({
            @Parameter(name="beverageName", description = "음료 이름"),
            @Parameter(name="userEmail", description = "유저 이메일 (즐겨찾기 조회용)")
    })
    public ResponseEntity<List<BeverageFindResponseDto>> findByBeverageName(@RequestParam(value = "beverageName") String beverageName,
                                                                            @RequestParam("userEmail") String userEmail){
        return new ResponseEntity<>(beverageService.findByBeverageName(beverageName, userEmail), HttpStatus.OK);
    }
}
