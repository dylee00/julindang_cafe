package com.POG.julindang.cafe.service;


import com.POG.julindang.cafe.domain.Topping;
import com.POG.julindang.cafe.dto.ToppingDto;
import com.POG.julindang.cafe.dto.ToppingFindDto;
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
public class ToppingService {
    private final ToppingRepository toppingRepository;

    public List<ToppingDto> findAll(){
        return toppingRepository.findAll().stream()
                .filter(x -> x.getDeleted() == false)
                .map(ToppingDto::new)
                .collect(Collectors.toList());
    }

    public List<ToppingDto> findAllByCafeNameAndBeverageName(ToppingFindDto toppingFindDto) throws CustomException {
        if(toppingFindDto==null){
            throw new CustomException(ErrorCode.OBJECT_NOT_FOUND);
        }

        String cafeName = toppingFindDto.getCafeName();
        String beverageName = toppingFindDto.getBeverageName();

        List<Topping> result = toppingRepository.findByCafeNameAndBeverageName(cafeName, beverageName)
                .orElseThrow(()-> new CustomException(ErrorCode.TOPPING_DOES_NOT_EXIST));

        return result.stream().filter(x -> x.getDeleted() == false)
                .map(ToppingDto::new)
                .collect(Collectors.toList());
    }

}
