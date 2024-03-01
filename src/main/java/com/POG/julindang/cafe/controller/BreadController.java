package com.POG.julindang.cafe.controller;


import com.POG.julindang.cafe.dto.response.bread.BreadNameResponseDto;
import com.POG.julindang.cafe.dto.response.bread.BreadResponseDto;
import com.POG.julindang.cafe.service.BreadService;
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
@RequestMapping("/cafe/bread")
@Tag(name = "bread")
public class BreadController {
    private final BreadService breadService;

    @GetMapping("/find-bread-names")
    public ResponseEntity<List<BreadNameResponseDto>> findBreadNamesByCafeName(
            @RequestParam(name = "cafeName", required = false) String cafeName){

        return new ResponseEntity<>(breadService.findBreadNamesByCafeName(cafeName), HttpStatus.OK);
    }

    @GetMapping("/find-bread-details")
    public ResponseEntity<List<BreadResponseDto>> findBreadDetailsByCafeNameAndBreadName(
            @RequestParam(name="cafeName", required = false) String cafeName,
            @RequestParam(name="breadName", required = false) String breadName){

        return new ResponseEntity<>(breadService.findBreadDetailsByCafeNameAndBreadName(cafeName,breadName), HttpStatus.OK);
    }
}
