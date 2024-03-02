package com.POG.julindang.cafe.controller;


import com.POG.julindang.cafe.dto.response.bread.BreadNameResponseDto;
import com.POG.julindang.cafe.dto.response.bread.BreadResponseDto;
import com.POG.julindang.cafe.service.BreadService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "카페 이름으로 빵류 찾기",
            description = "카페 이름으로 해당 카페에 존재하는 빵류 로드")
    @GetMapping("/find-bread-names")
    public ResponseEntity<List<BreadNameResponseDto>> findBreadNamesByCafeName(
            @RequestParam(name = "cafeName", required = false) String cafeName){

        return new ResponseEntity<>(breadService.findBreadNamesByCafeName(cafeName), HttpStatus.OK);
    }

    @Operation(summary = "카페 이름과 빵류 이름으로 해당 빵류 정보 로드",
            description = "카페 이름과 빵류 이름으로 해당 빵류 정보의 상세 정보 로드")
    @GetMapping("/find-bread-details")
    public ResponseEntity<List<BreadResponseDto>> findBreadDetailsByCafeNameAndBreadName(
            @RequestParam(name="cafeName", required = false) String cafeName,
            @RequestParam(name="breadName", required = false) String breadName){

        return new ResponseEntity<>(breadService.findBreadDetailsByCafeNameAndBreadName(cafeName,breadName), HttpStatus.OK);
    }
}
