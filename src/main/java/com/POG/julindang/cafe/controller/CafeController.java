package com.POG.julindang.cafe.controller;

import com.POG.julindang.cafe.dto.response.BeverageNameDto;
import com.POG.julindang.cafe.dto.response.CafeDto;
import com.POG.julindang.cafe.dto.response.CafeNameDto;
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
@Tag(name = "카페 관련 컨트롤러")
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
    public ResponseEntity<List<CafeDto>> findByCafeName(@RequestParam(name = "cafeName", required = false) String cafeName){
        return new ResponseEntity(cafeService.findByCafeName(cafeName), HttpStatus.OK);
    }

    @Operation(summary = "음료 이름을 통해 탐색",
            description = "음료 이름과 일치하는 음료 정보들 로드")
    @GetMapping("/find-by-beverage-name")
    public ResponseEntity<List<CafeDto>> findByBeverageName(@RequestParam(name="beverageName", required = false) String beverageName){
        return new ResponseEntity(cafeService.findByBeverageName(beverageName), HttpStatus.OK);
    }


//    @Operation(summary = "카페 이름과 음료 이름을 통해 탐색",
//            description = "카페 이름과 음료 이름과 일치하는 음료 정보들 로드")
//    @PostMapping("/find-by-cafe-name-and-beverage")
//    public ResponseEntity<List<CafeDto>> findByCafeNameAndBeverageName(@RequestBody CafeFindDto cafeFindDto) {
//        return new ResponseEntity(cafeService.findByCafeNameAndBeverageName(cafeFindDto), HttpStatus.OK);
//    }


    @Operation(summary = "해당 카페에 포함된 음료 탐색",
        description = "음료 이름만 리턴")
    @GetMapping("/find-beverage-name-by-cafe-name")
    public ResponseEntity<List<BeverageNameDto>> findBeverageNameByCafeName(@RequestParam(name = "cafeName", required = false) String cafeName){
        return new ResponseEntity<>(cafeService.findBeverageName(cafeName), HttpStatus.OK);
    }

    @Operation(summary = "카페 이름 리턴",
            description = "카페 이름들만 리턴")
    @GetMapping("/find-cafe-name")
    public ResponseEntity<List<CafeNameDto>> findCafeNames(){
        return new ResponseEntity<>(cafeService.findCafeName(), HttpStatus.OK);
    }

}
