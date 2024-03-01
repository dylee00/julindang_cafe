package com.POG.julindang.cafe.controller;

import com.POG.julindang.cafe.dto.response.cafe.BeverageNameResponseDto;
import com.POG.julindang.cafe.dto.response.cafe.CafeResponseDto;
import com.POG.julindang.cafe.dto.response.cafe.CafeNameResponseDto;
import com.POG.julindang.cafe.service.CafeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/cafe")
@Tag(name = "cafe")
public class CafeController {
    private final CafeService cafeService;

//    @Operation(summary = "음료 전체 탐색",
//            description = "모든 음료 정보 로드")
//    @GetMapping
//    public ResponseEntity<List<CafeDto>> findAll(){
//        return new ResponseEntity(cafeService.findAll(), HttpStatus.OK);
//    }

    @Operation(summary = "카페 이름을 통해 탐색",
            description = "카페 이름과 일치하는 음료 정보들 로드")
    @GetMapping("/find-by-cafe-name")
    public ResponseEntity<List<CafeResponseDto>> findByCafeName(@RequestParam(name = "cafeName", required = false) String cafeName){
        return new ResponseEntity(cafeService.findByCafeName(cafeName), HttpStatus.OK);
    }

    @Operation(summary = "음료 이름을 통해 탐색",
            description = "음료 이름과 일치하는 음료 정보들 로드")
    @GetMapping("/find-by-beverage-name")
    public ResponseEntity<List<CafeResponseDto>> findByBeverageName(@RequestParam(name="beverageName", required = false) String beverageName){
        return new ResponseEntity(cafeService.findByBeverageName(beverageName), HttpStatus.OK);
    }


    @Operation(summary = "카페 이름과 음료 이름을 통해 탐색",
            description = "카페 이름과 음료 이름과 일치하는 음료 디테일 정보들 로드")
    @GetMapping("/find-by-cafe-name-and-beverage")
    public ResponseEntity<List<CafeResponseDto>> findByCafeNameAndBeverageName(@RequestParam(name="cafeName", required = false) String cafeName,
                                                                               @RequestParam(name = "beverageName", required = false) String beverageName) {
        return new ResponseEntity(cafeService.findCafeDetailsByCafeNameAndBeverageName(cafeName, beverageName), HttpStatus.OK);
    }


    @Operation(summary = "해당 카페에 포함된 음료 탐색",
        description = "음료 이름만 리턴")
    @GetMapping("/find-beverage-name-by-cafe-name")
    public ResponseEntity<List<BeverageNameResponseDto>> findBeverageNameByCafeName(@RequestParam(name = "cafeName", required = false) String cafeName){
        return new ResponseEntity<>(cafeService.findBeverageName(cafeName), HttpStatus.OK);
    }

    @Operation(summary = "카페 이름 리턴",
            description = "카페 이름들만 리턴")
    @GetMapping("/find-cafe-name")
    public ResponseEntity<List<CafeNameResponseDto>> findCafeNames(){
        return new ResponseEntity<>(cafeService.findCafeName(), HttpStatus.OK);
    }

}
