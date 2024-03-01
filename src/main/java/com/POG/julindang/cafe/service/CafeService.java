package com.POG.julindang.cafe.service;

import com.POG.julindang.cafe.domain.Cafe;
import com.POG.julindang.cafe.dto.response.cafe.BeverageNameResponseDto;
import com.POG.julindang.cafe.dto.response.cafe.CafeResponseDto;
import com.POG.julindang.cafe.dto.response.cafe.CafeNameResponseDto;
import com.POG.julindang.cafe.repository.CafeRepository;
import com.POG.julindang.common.exception.cafe.BeverageNameDoesNotExist;
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
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CafeService {
    private final CafeRepository cafeRepository;
//    public List<CafeDto> findAll(){
//       List<Cafe> result = cafeRepository.findAll();
//
//        return result.stream()
//                .filter(x -> x.getDeleted() == false)
//                .map(CafeDto::new)
//                .collect(Collectors.toList());
//    }

    public List<CafeResponseDto> findByCafeName(String cafeName) {
        if(cafeName == null){
            throw new CafeNameDoesNotExist();
        }
        List<Cafe> result = cafeRepository.findByCafeName(cafeName);

        return result.stream()
                .filter(x -> x.getDeleted() == false)
                .map(CafeResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<CafeResponseDto> findByBeverageName(String beverageName) {
        if(beverageName == null){
            throw new BeverageNameDoesNotExist();
        }
        List<Cafe> result = cafeRepository.findByBeverageName(beverageName);

        return result.stream().filter(x -> x.getDeleted() == false).map(CafeResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<CafeResponseDto> findCafeDetailsByCafeNameAndBeverageName(String cafeName, String beverageName) {
        if(cafeName == null){
            throw new CafeNameDoesNotExist();
        }
        if(beverageName == null){
            throw new BeverageNameDoesNotExist();
        }
        List<Cafe> cafes = cafeRepository.findByCafeNameAndBeverageName(cafeName, beverageName);

        return cafes.stream()
                .map(CafeResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<BeverageNameResponseDto> findBeverageName(String cafeName){
        AtomicLong id = new AtomicLong(0);
        List<String> byCafeName = cafeRepository.findDistinctByCafeName(cafeName);
        List<BeverageNameResponseDto> result = new ArrayList<>();

        for (String s : byCafeName) {
            BeverageNameResponseDto build = BeverageNameResponseDto.builder()
                    .id(id.getAndIncrement())
                    .beverageName(s).build();
            result.add(build);
        }
        return result;
    }

    public List<CafeNameResponseDto> findCafeName(){
        AtomicLong id = new AtomicLong(0);
        List<String> cafeNames = cafeRepository.findDistinctCafeName();
        List<CafeNameResponseDto> result = new ArrayList<>();

        for (String cafeName : cafeNames) {
            CafeNameResponseDto build = CafeNameResponseDto.builder()
                    .id(id.getAndIncrement())
                    .cafeName(cafeName).build();
            result.add(build);
        }
        return result;
    }
}
