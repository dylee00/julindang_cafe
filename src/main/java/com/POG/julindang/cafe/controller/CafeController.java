package com.POG.julindang.cafe.controller;


import com.POG.julindang.cafe.dto.CafeDto;
import com.POG.julindang.cafe.dto.CafeFindDto;
import com.POG.julindang.cafe.service.CafeService;
import com.POG.julindang.common.exception.CustomException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
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

    @Operation(summary = "음료 전체 탐색",
            description = "모든 음료 정보 로드")
    @GetMapping
    public ResponseEntity<List<CafeDto>> findAll(){
        return new ResponseEntity(cafeService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "카페 이름을 통해 탐색",
            description = "카페 이름과 일치하는 음료 정보들 로드")
    @PostMapping("/find-by-cafe-name")
    public ResponseEntity<List<CafeDto>> findByCafeName(@RequestBody CafeFindDto cafeFindDto) throws CustomException {
        return new ResponseEntity(cafeService.findByCafeName(cafeFindDto), HttpStatus.OK);
    }

    @Operation(summary = "음료 이름을 통해 탐색",
            description = "음료 이름과 일치하는 음료 정보들 로드")
    @PostMapping("/find-by-beverage-name")
    public ResponseEntity<List<CafeDto>> findByBeverageName(@RequestBody CafeFindDto cafeFindDto) throws CustomException {
        return new ResponseEntity(cafeService.findByBeverageName(cafeFindDto), HttpStatus.OK);
    }

    @Operation(summary = "카페 이름과 음료 이름을 통해 탐색",
            description = "카페 이름과 음료 이름과 일치하는 음료 정보 (하나) 로드")
    @PostMapping("find-by-cafe-name-and-beverage-name")
    public ResponseEntity<CafeDto> findByCafeNameAndBeverageName(@RequestBody CafeFindDto cafeFindDto) throws CustomException {
        return new ResponseEntity(cafeService.findByCafeNameAndBeverageName(cafeFindDto), HttpStatus.OK);
    }

    @Operation(summary = "음료 저장",
            description = "해당 음료를 db에 저장")
    @PostMapping("/save")
    public ResponseEntity<CafeDto> save(@RequestBody CafeDto cafeDto) throws CustomException {
        return new ResponseEntity(cafeService.save(cafeDto), HttpStatus.CREATED);
    }

    @Operation(summary = "음료 삭제",
            description = "해당 음료 삭제")
    @PostMapping("/delete")
    public ResponseEntity<CafeDto> delete(@RequestBody CafeFindDto cafeFindDto) throws CustomException {
        return new ResponseEntity(cafeService.delete(cafeFindDto), HttpStatus.OK);
    }
}
