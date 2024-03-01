package com.POG.julindang.cafe.service;


import com.POG.julindang.cafe.domain.Topping;
import com.POG.julindang.cafe.dto.response.cafe.ToppingResponseDto;

import com.POG.julindang.cafe.repository.ToppingRepository;
import com.POG.julindang.common.exception.cafe.BeverageNameDoesNotExist;
import com.POG.julindang.common.exception.cafe.CafeNameDoesNotExist;
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

    public List<ToppingResponseDto> findAll(){
        return toppingRepository.findAll().stream()
                .filter(x -> x.getDeleted() == false)
                .map(ToppingResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<ToppingResponseDto> findAllByCafeNameAndBeverageName(String cafeName, String beverageName) {
        if(cafeName == null){
            throw new CafeNameDoesNotExist();
        }
        if(beverageName==null){
            throw new BeverageNameDoesNotExist();
        }
        List<Topping> result = toppingRepository.findByCafeNameAndBeverageName(cafeName, beverageName);


        return result.stream().filter(x -> x.getDeleted() == false)
                .map(ToppingResponseDto::new)
                .collect(Collectors.toList());
    }

}
