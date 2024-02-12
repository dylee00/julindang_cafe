package com.POG.julindang.cafe.service;

import com.POG.julindang.cafe.domain.Cafe;
import com.POG.julindang.cafe.dto.CafeDto;
import com.POG.julindang.cafe.dto.CafeFindDto;
import com.POG.julindang.cafe.repository.CafeRepository;
import com.POG.julindang.common.exception.CustomException;
import com.POG.julindang.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CafeService {
    private final CafeRepository cafeRepository;
    public List<CafeDto> findAll(){
       List<Cafe> result = cafeRepository.findAll();

        return result.stream().filter(x -> x.getDeleted() == false).map(CafeDto::new)
                .collect(Collectors.toList());
    }

    public List<CafeDto> findByCafeName(CafeFindDto cafeFindDto) throws CustomException {
        if(cafeFindDto == null){
            throw new CustomException(ErrorCode.OBJECT_NOT_FOUND);
        }
        String cafeName = cafeFindDto.getCafeName();
        if(cafeName == null){
            throw new CustomException(ErrorCode.CAFE_NAME_DOES_NOT_EXIST);
        }
        List<Cafe> result = cafeRepository.findByCafeName(cafeName);

        return result.stream().filter(x -> x.getDeleted() == false).map(CafeDto::new)
                .collect(Collectors.toList());
    }

    public List<CafeDto> findByBeverageName(CafeFindDto cafeFindDto) throws CustomException {
        if(cafeFindDto == null){
            throw new CustomException(ErrorCode.OBJECT_NOT_FOUND);
        }
        String beverageName = cafeFindDto.getBeverageName();
        if(beverageName == null){
            throw new CustomException(ErrorCode.BEVERAGE_NAME_DOES_NOT_EXIST);
        }
        List<Cafe> result = cafeRepository.findByBeverageName(beverageName);

        return result.stream().filter(x -> x.getDeleted() == false).map(CafeDto::new)
                .collect(Collectors.toList());
    }

    public CafeDto findByCafeNameAndBeverageNameAndSize(CafeFindDto cafeFindDto) throws CustomException {
        if(cafeFindDto == null){
            throw new CustomException(ErrorCode.OBJECT_NOT_FOUND);
        }
        String cafeName = cafeFindDto.getCafeName();
        String beverageName = cafeFindDto.getBeverageName();
        String size = cafeFindDto.getSize();
        if(cafeName == null){
            throw new CustomException(ErrorCode.CAFE_NAME_DOES_NOT_EXIST);
        }
        if(beverageName == null){
            throw new CustomException(ErrorCode.BEVERAGE_NAME_DOES_NOT_EXIST);
        }
        if(size == null){
            throw new CustomException(ErrorCode.OBJECT_NOT_FOUND);
        }
        Cafe findCafe = cafeRepository.findByCafeNameAndBeverageNameAndSize(cafeName, beverageName, size)
                .orElseThrow(() -> new CustomException(ErrorCode.CAFE_DOES_NOT_EXIST));

        return  CafeDto.builder()
                .temperature(findCafe.getTemperature())
                .sugar(findCafe.getSugar())
                .size(findCafe.getSize())
                .serve(findCafe.getServe())
                .beverageName(findCafe.getBeverageName())
                .calorie(findCafe.getCalorie())
                .cafeName(findCafe.getCafeName())
                .build();
    }

}
