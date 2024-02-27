package com.POG.julindang.cafe.service;

import com.POG.julindang.cafe.domain.Cafe;
import com.POG.julindang.cafe.dto.response.BeverageNameDto;
import com.POG.julindang.cafe.dto.response.CafeDto;
import com.POG.julindang.cafe.dto.response.CafeNameDto;
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

    public List<CafeDto> findByCafeName(String cafeName) {
        if(cafeName == null){
            throw new CafeNameDoesNotExist();
        }
        List<Cafe> result = cafeRepository.findByCafeName(cafeName);

        return result.stream()
                .filter(x -> x.getDeleted() == false)
                .map(CafeDto::new)
                .collect(Collectors.toList());
    }

    public List<CafeDto> findByBeverageName(String beverageName) {
        if(beverageName == null){
            throw new BeverageNameDoesNotExist();
        }
        List<Cafe> result = cafeRepository.findByBeverageName(beverageName);

        return result.stream().filter(x -> x.getDeleted() == false).map(CafeDto::new)
                .collect(Collectors.toList());
    }

//    public List<CafeDto> findByCafeNameAndBeverageName(CafeFindDto cafeFindDto) {
//        String cafeName = cafeFindDto.getCafeName();
//        String beverageName = cafeFindDto.getBeverageName();
//        if(cafeName == null){
//            throw new CafeNameDoesNotExist();
//        }
//        if(beverageName == null){
//            throw new BeverageNameDoesNotExist();
//        }
//        List<Cafe> cafes = cafeRepository.findByCafeNameAndBeverageName(cafeName, beverageName);
//
//        return cafes.stream()
//                .map(CafeDto::new)
//                .collect(Collectors.toList());
//    }

    public List<BeverageNameDto> findBeverageName(String cafeName){
        AtomicLong id = new AtomicLong(0);
        List<String> byCafeName = cafeRepository.findDistinctByCafeName(cafeName);
        List<BeverageNameDto> result = new ArrayList<>();

        for (String s : byCafeName) {
            BeverageNameDto build = BeverageNameDto.builder()
                    .id(id.getAndIncrement())
                    .beverageName(s).build();
            result.add(build);
        }
        return result;
    }

    public List<CafeNameDto> findCafeName(){
        AtomicLong id = new AtomicLong(0);
        List<String> cafeNames = cafeRepository.findDistinctCafeName();
        List<CafeNameDto> result = new ArrayList<>();

        for (String cafeName : cafeNames) {
            CafeNameDto build = CafeNameDto.builder()
                    .id(id.getAndIncrement())
                    .cafeName(cafeName).build();
            result.add(build);
        }
        return result;
    }
}
