package com.POG.julindang.cafe.service;


import com.POG.julindang.cafe.domain.Dessert;
import com.POG.julindang.cafe.dto.response.dessert.DessertNameResponseDto;
import com.POG.julindang.cafe.dto.response.dessert.DessertResponseDto;
import com.POG.julindang.cafe.repository.DessertRepository;
import com.POG.julindang.common.exception.dessert.DessertNameDoesNotExist;
import com.POG.julindang.common.exception.cafe.CafeNameDoesNotExist;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class DessertService {
    private final DessertRepository dessertRepository;

    public List<DessertNameResponseDto> findBreadNamesByCafeName(String cafeName){
        List<Dessert> breadNamesByCafeName = dessertRepository.findBreadNamesByCafeName(cafeName);
        AtomicLong id = new AtomicLong(0);
        List<DessertNameResponseDto> result = new ArrayList<>();
        for (Dessert bread : breadNamesByCafeName) {
            DessertNameResponseDto build = DessertNameResponseDto.builder()
                    .id(id.getAndIncrement())
                    .dessertName(bread.getDessertName())
                    .build();
            result.add(build);
        }

        return result;
    }

    public List<DessertResponseDto> findBreadDetailsByCafeNameAndBreadName(String cafeName, String dessertName){
        if(cafeName == null){
            throw new CafeNameDoesNotExist();
        }

        if(dessertName==null){
            throw new DessertNameDoesNotExist();
        }

        List<Dessert> result = dessertRepository.findBreadByCafeNameAndDessertName(cafeName, dessertName);

        return result.stream()
                .map(DessertResponseDto::new)
                .collect(Collectors.toList());
    }
}
