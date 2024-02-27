package com.POG.julindang.cafe.controller;


import com.POG.julindang.cafe.dto.response.ToppingDto;
import com.POG.julindang.cafe.service.ToppingService;
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
@RequestMapping("/topping")
@Tag(name = "topping")
public class ToppingController {
    private final ToppingService toppingService;

    @GetMapping
    @Operation(summary = "모든 토핑 불러오기",
            description = "모든 토핑 로드")
    public ResponseEntity<List<ToppingDto>> findAll(){
        return new ResponseEntity<>(toppingService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/find-by-cafe-name-and-beverage-name")
    @Operation(summary = "카페 이름과 음료를 이용해 해당되는 토핑 정보 로드",
            description = "카페 이름과 음료와 일치하는 토핑 정보들 로드")
    public ResponseEntity<List<ToppingDto>> findToppingsByCafeNameAndBeverageName(@RequestParam(name="cafeName", required = false) String cafeName,
                                                                                  @RequestParam(name = "beverageName", required = false) String beverageName) {
        return new ResponseEntity<>(toppingService.findAllByCafeNameAndBeverageName(cafeName, beverageName), HttpStatus.OK);
    }
}
