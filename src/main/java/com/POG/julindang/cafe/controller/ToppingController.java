package com.POG.julindang.cafe.controller;


import com.POG.julindang.cafe.dto.ToppingDto;
import com.POG.julindang.cafe.dto.ToppingFindDto;
import com.POG.julindang.cafe.service.ToppingService;
import com.POG.julindang.common.exception.CustomException;
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
@RequestMapping("/cafe/topping")
@Tag(name = "카페 관련 컨트롤러")
public class ToppingController {
    private final ToppingService toppingService;

    @GetMapping
    public ResponseEntity<List<ToppingDto>> findAll(){
        return new ResponseEntity<>(toppingService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/find-by-cafe-name-and-beverage-name")
    public ResponseEntity<List<ToppingDto>> findToppingsByCafeNameAndBeverageName(ToppingFindDto toppingFindDto) throws CustomException {
        return new ResponseEntity<>(toppingService.findAllByCafeNameAndBeverageName(toppingFindDto), HttpStatus.OK);
    }
}
