package com.POG.julindang.cafe.controller;

import com.POG.julindang.cafe.dto.response.beverage.BeverageDetailResponseDto;
import com.POG.julindang.cafe.dto.response.beverage.BeverageFindResponseDto;
import com.POG.julindang.cafe.dto.response.common.CommonResponseDto;
import com.POG.julindang.cafe.service.BeverageService;
import com.POG.julindang.cafe.service.CafeServiceImpl;
import com.POG.julindang.cafe.service.DessertServiceImpl;
import com.POG.julindang.cafe.vo.BeverageDetailVo;
import com.POG.julindang.common.exception.beverage.ParameterInvalidException;
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
@RequestMapping("/cafe/beverage")
@Tag(name = "beverage")
public class BeverageController {
    private final BeverageService beverageService;
    private final DessertServiceImpl dessertServiceImpl;
    private final CafeServiceImpl cafeService;

    //카페 + 음료 상세 정보
    @Operation(description = "음료 이름과 카페 이름으로 디테일 불러오기")
    @GetMapping("/details")
    @Parameters({
            @Parameter(name="cafeName", description = "카페 이름"),
            @Parameter(name="beverageName", description = "음료 이름")

    })
    public ResponseEntity<List<BeverageDetailVo>> findByCafeNameAndBeverageName(@RequestParam(value = "cafeName") String cafeName,
                                                                                         @RequestParam(value = "beverageName") String beverageName){
        return ResponseEntity.ok(beverageService.findBeverageDetails(cafeName, beverageName));
    }

    //카페이름 + 음료 -> 조건: 즐겨찾기 나오고 가나다순 정렬
    @Operation(description = "카페 이름에 따른 음료들 불러오기")
    @GetMapping("/by-cafe-name")
    @Parameters({
            @Parameter(name="cafeName", description = "카페 이름")
    })
    public ResponseEntity<List<BeverageFindResponseDto>> findByCafeName(@RequestParam(value = "cafeName") String cafeName){
        return ResponseEntity.ok(beverageService.findByCafeName(cafeName));
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
    public ResponseEntity<Object> findAll(@RequestParam Long type, @RequestParam Long sort){
        if (type == 0)
            return ResponseEntity.ok(cafeService.getBeveragesAndDesserts(sort));
        else if (type == 1)
            return ResponseEntity.ok(beverageService.getBeverageList(sort));
        else if (type == 2) {
            return ResponseEntity.ok(dessertServiceImpl.getDessertList(sort));
        }
        else throw new ParameterInvalidException("Parameter type can be 0 or 1 or 2, but type: " + type);
    }

    @Operation(description = "cafeId에 따른 세부 정보")
    @GetMapping("/details/cafeId")
    public ResponseEntity<List<BeverageDetailVo>> getDetails(@RequestParam Long dessertId) {
        return ResponseEntity.ok(beverageService.getDetails(dessertId));
    }
}
