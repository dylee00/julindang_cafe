package com.POG.julindang.cafe.service;


import com.POG.julindang.cafe.dto.response.cafe.CafeLikeResponseDto;
import com.POG.julindang.cafe.dto.response.cafe.CafeResponseDto;
import com.POG.julindang.cafe.repository.CafeImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CafeService {
    private final CafeImageRepository cafeImageRepository;


    // 카페 정보 불러오기
    public List<CafeLikeResponseDto> findCafeNames(){
        return cafeImageRepository.getAllCafeImages();
    }

    // 카페 검색
    public List<CafeResponseDto> findByCafeName(String cafeName){
        return cafeImageRepository.getCafeName(cafeName);
    }
}
