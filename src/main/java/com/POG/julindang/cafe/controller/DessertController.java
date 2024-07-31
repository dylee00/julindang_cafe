package com.POG.julindang.cafe.controller;


import com.POG.julindang.cafe.dto.response.dessert.DessertDetailResponseDto;
import com.POG.julindang.cafe.dto.response.dessert.DessertFindResponseDto;
import com.POG.julindang.cafe.service.DessertService;
import com.POG.julindang.cafe.vo.BeverageDetailVo;
import com.POG.julindang.cafe.vo.DessertDetailVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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

    //카페+디저트의 상세 정보
    @Operation(description = "디저트 이름과 카페 이름으로 디테일 불러오기")
    @GetMapping("/details")
    @Parameters({
            @Parameter(name="cafeName", description = "카페 이름"),
            @Parameter(name="beverageName", description = "디저트 이름")
    })
    public ResponseEntity<List<DessertDetailVo>> findByCafeNameAndDessertName(@RequestParam(value = "cafeName") String cafeName,
                                                                                        @RequestParam(value = "beverageName") String beverageName){
        return ResponseEntity.ok(dessertService.findDessertDetails(cafeName, beverageName));
    }

    //카페이름 + 디저트 -> 조건: 즐겨찾기 나오고 가나다순 정렬
    @Operation(description = "카페 이름에 따른 디저트들 불러오기")
    @GetMapping("/by-cafe-name")
    @Parameters({
            @Parameter(name="cafeName", description = "카페 이름"),
    })
    public ResponseEntity<List<DessertFindResponseDto>> findByCafeName(@RequestParam(value = "cafeName") String cafeName){
        return ResponseEntity.ok(dessertService.findByCafeName(cafeName));
    }


    @Operation(description = "디저트 정보 전체 불러오기")
    @GetMapping
    public ResponseEntity<List<DessertFindResponseDto>> findAll(){
        return ResponseEntity.ok(dessertService.findAll());
    }

    @Operation(description = "디저트 이름에 따른 디저트들 불러오기")
    @GetMapping("/by-dessert-name")
    @Parameters({
            @Parameter(name="dessertName", description = "디저트 이름"),
    })
    public ResponseEntity<List<DessertFindResponseDto>> findByDessertName(@RequestParam(value = "dessertName") String dessertName){
        return ResponseEntity.ok(dessertService.findByDessertName(dessertName));
    }

    @Operation(description = "dessertId에 따른 세부 정보")
    @GetMapping("/details/dessertId")
    public ResponseEntity<List<DessertDetailVo>> getDetails(@RequestParam Long dessertId) {
        return ResponseEntity.ok(dessertService.getDetails(dessertId));
    }
}
