package com.POG.julindang.cafe.service;


import com.POG.julindang.cafe.domain.Bread;
import com.POG.julindang.cafe.dto.response.bread.BreadNameResponseDto;
import com.POG.julindang.cafe.dto.response.bread.BreadResponseDto;
import com.POG.julindang.cafe.repository.BreadRepository;
import com.POG.julindang.common.exception.bread.BreadNameDoesNotExist;
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
public class BreadService {
    private final BreadRepository breadRepository;

    public List<BreadNameResponseDto> findBreadNamesByCafeName(String cafeName){
        List<Bread> breadNamesByCafeName = breadRepository.findBreadNamesByCafeName(cafeName);
        AtomicLong id = new AtomicLong(0);
        List<BreadNameResponseDto> result =new ArrayList<>();
        for (Bread bread : breadNamesByCafeName) {
            if(bread.getDeleted() == false) {
                BreadNameResponseDto build = BreadNameResponseDto.builder()
                        .id(id.getAndIncrement())
                        .breadName(bread.getBreadName())
                        .build();
                result.add(build);
            }
        }

        return result;
    }

    public List<BreadResponseDto> findBreadDetailsByCafeNameAndBreadName(String cafeName, String breadName){
        if(cafeName == null){
            throw new CafeNameDoesNotExist();
        }

        if(breadName==null){
            throw new BreadNameDoesNotExist();
        }
        List<Bread> result = breadRepository.findBreadByCafeNameAndBreadName(cafeName, breadName);

        return result.stream()
                .filter(x -> x.getDeleted() == false)
                .map(BreadResponseDto::new)
                .collect(Collectors.toList());
    }
}
