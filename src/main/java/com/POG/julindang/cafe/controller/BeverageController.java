package com.POG.julindang.cafe.controller;

import com.POG.julindang.cafe.dto.response.beverage.BeverageDetailResponseDto;
import com.POG.julindang.cafe.dto.response.beverage.BeverageFindResponseDto;
import com.POG.julindang.cafe.dto.response.common.CommonResponseDto;
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

    @Operation(description = "음료 이름과 카페 이름으로 디테일 불러오기")
    @GetMapping("/details")
    @Parameters({
            @Parameter(name="cafeName", description = "카페 이름"),
            @Parameter(name="beverageName", description = "음료 이름")

    })
    public ResponseEntity<List<BeverageDetailResponseDto>> findByCafeNameAndBeverageName(@RequestParam(value = "cafeName") String cafeName,
                                                                                         @RequestParam(value = "beverageName") String beverageName){
        return new ResponseEntity<>(beverageService.findBeverageDetails(cafeName, beverageName), HttpStatus.OK);
    }

    @Operation(description = "카페 이름에 따른 음료들 불러오기")
    @GetMapping("/by-cafe-name")
    @Parameters({
            @Parameter(name="cafeName", description = "카페 이름")
    })
    public ResponseEntity<List<BeverageFindResponseDto>> findByCafeName(@RequestParam(value = "cafeName") String cafeName){
        return new ResponseEntity<>(beverageService.findByCafeName(cafeName), HttpStatus.OK);
    }

    @Operation(description = "음료 이름에 따른 음료들 불러오기")
    @GetMapping("/by-beverage-name")
    @Parameters({
            @Parameter(name="beverageName", description = "음료 이름")
    })
    public ResponseEntity<List<BeverageFindResponseDto>> findByBeverageName(@RequestParam(value = "beverageName") String beverageName){
        return ResponseEntity.ok(beverageService.findByBeverageName(beverageName));
    }

    @Operation(description = "홈 화면의 음료/디저트 항목들")
    @GetMapping("/all")
    public ResponseEntity<List<CommonResponseDto>> findAll(){
        return ResponseEntity.ok(null);
    }
}
