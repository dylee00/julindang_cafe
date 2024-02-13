package com.POG.julindang.cafe.service;


import com.POG.julindang.cafe.domain.Cafe;
import com.POG.julindang.cafe.domain.Topping;
import com.POG.julindang.cafe.dto.*;
import com.POG.julindang.cafe.repository.CafeRepository;
import com.POG.julindang.cafe.repository.ToppingRepository;
import com.POG.julindang.common.exception.CustomException;
import com.POG.julindang.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;



@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AdminService {
    private final CafeRepository cafeRepository;
    private final ToppingRepository toppingRepository;

    public AdminCafeDto toggleDeleteCafe(Long cafeId) throws CustomException {
        Cafe cafe = cafeRepository.findById(cafeId)
                .orElseThrow(()->new CustomException(ErrorCode.CAFE_DOES_NOT_EXIST));

        cafe.setDeleted(!cafe.getDeleted());

        return new AdminCafeDto(cafe);
    }

    public AdminToppingDto toggleDeleteTopping(Long toppingId) throws CustomException {
        Topping topping = toppingRepository.findById(toppingId)
                .orElseThrow(() -> new CustomException(ErrorCode.TOPPING_DOES_NOT_EXIST));

        topping.setDeleted(!topping.getDeleted());
        return new AdminToppingDto(topping);
    }

    public List<AdminCafeDto> findAllCafes(){
        List<Cafe> result = cafeRepository.findAll();

        return result.stream()
                .map(AdminCafeDto::new)
                .collect(Collectors.toList());
    }

    public List<AdminToppingDto> findAllToppings(){
        List<Topping> result = toppingRepository.findAll();

        return result.stream()
                .map(AdminToppingDto::new)
                .collect(Collectors.toList());
    }

    public CafeSaveDto saveCafe(CafeSaveDto cafeSaveDto) throws CustomException {
        if(cafeSaveDto == null){
            throw new CustomException(ErrorCode.OBJECT_NOT_FOUND);
        }
        if(cafeSaveDto.getCafeName() == null){
            throw new CustomException(ErrorCode.CAFE_NAME_DOES_NOT_EXIST);
        }
        if(cafeSaveDto.getBeverageName() == null){
            throw new CustomException(ErrorCode.BEVERAGE_NAME_DOES_NOT_EXIST);
        }

        cafeRepository.save(cafeSaveDto.toEntity());
        return cafeSaveDto;
    }

    public ToppingSaveDto saveTopping(ToppingSaveDto toppingSaveDto) throws CustomException {
        if(toppingSaveDto == null){
            throw new CustomException(ErrorCode.OBJECT_NOT_FOUND);
        }
        if(toppingSaveDto.getToppingName() == null){
            throw new CustomException(ErrorCode.TOPPING_NAME_DOES_NOT_EXIST);
        }

        toppingRepository.save(toppingSaveDto.toEntity());
        return toppingSaveDto;
    }

    public AdminToppingDto updateTopping(AdminToppingDto adminToppingDto) throws CustomException {
        return null;
    }
    public AdminCafeDto updateCafe(AdminCafeDto adminCafeDto){
        return null;
    }
}

