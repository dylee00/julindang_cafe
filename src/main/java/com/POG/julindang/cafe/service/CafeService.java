package com.POG.julindang.cafe.service;


import com.POG.julindang.cafe.domain.CafeImage;
import com.POG.julindang.cafe.dto.response.cafe.CafeFindResponseDto;

import com.POG.julindang.cafe.repository.CafeImageRepository;

import com.POG.julindang.common.exception.cafe.CafeDoesNotExist;

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
    private final CafeImageRepository cafeImageRepository;


    // 카페 정보 불러오기
    public List<CafeFindResponseDto> findCafeNames(){
        List<CafeImage> result = cafeImageRepository.findAll();

        return result.stream()
                .map(CafeFindResponseDto::new)
                .collect(Collectors.toList());
    }
    // 카페 검색
    public CafeFindResponseDto findByCafeName(String cafeName){
        CafeImage result = cafeImageRepository.findByCafeName(cafeName).orElseThrow(() -> new CafeDoesNotExist(cafeName));
        return CafeFindResponseDto.builder()
                .cafeName(result.getCafeName())
                .url(result.getUrl())
                .build();
    }
}
