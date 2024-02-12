package com.POG.julindang.cafe.service;


import com.POG.julindang.cafe.dto.ToppingDto;
import com.POG.julindang.cafe.repository.ToppingRepository;
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


    /**
     *  TODO : 서비스 구축 더 해됨
     *  find by 더 만들어라
     *
     *
     */
    public List<ToppingDto> findAll(){
        return toppingRepository.findAll().stream()
                .filter(x -> x.getDeleted() == false)
                .map(ToppingDto::new)
                .collect(Collectors.toList());
    }



}
