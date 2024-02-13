package com.POG.julindang.cafe.controller;

import com.POG.julindang.cafe.dto.*;
import com.POG.julindang.cafe.service.AdminService;
import com.POG.julindang.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    @GetMapping("/cafe")
    public ResponseEntity<List<AdminCafeDto>> findAllCafes(){
        return new ResponseEntity(adminService.findAllCafes(), HttpStatus.OK);
    }

    @GetMapping("/topping")
    public ResponseEntity<List<AdminToppingDto>> findAllToppings(){
        return new ResponseEntity(adminService.findAllToppings(), HttpStatus.OK);
    }

    @PostMapping("/cafe/save")
    public ResponseEntity<CafeSaveDto> saveCafe(@RequestBody CafeSaveDto cafeSaveDto) throws CustomException {
        return new ResponseEntity(adminService.saveCafe(cafeSaveDto), HttpStatus.CREATED);
    }

    @PostMapping("/topping/save")
    public ResponseEntity<ToppingSaveDto> save(@RequestBody ToppingSaveDto toppingSaveDto) throws CustomException {
        return new ResponseEntity(adminService.saveTopping(toppingSaveDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/cafe/delete/{id}")
    public ResponseEntity<AdminCafeDto> toggleDeleteCafe(@PathVariable Long id) throws CustomException {
        return new ResponseEntity(adminService.toggleDeleteCafe(id), HttpStatus.OK);
    }

    @DeleteMapping("/topping/delete/{id}")
    public ResponseEntity<AdminToppingDto> toggleDeleteTopping(@PathVariable Long id) throws CustomException {
        return new ResponseEntity(adminService.toggleDeleteTopping(id), HttpStatus.OK);
    }
}
