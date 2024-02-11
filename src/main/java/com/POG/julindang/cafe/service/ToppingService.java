package com.POG.julindang.cafe.service;


import com.POG.julindang.cafe.dto.ToppingDto;
import com.POG.julindang.cafe.dto.ToppingSaveDto;
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

    public List<ToppingDto> findAll(){
        return toppingRepository.findAll().stream()
                .map(ToppingDto::new)
                .collect(Collectors.toList());
    }

    public ToppingSaveDto save(ToppingSaveDto toppingSaveDto){
        toppingRepository.save(toppingSaveDto.toEntity());
        return toppingSaveDto;
    }

    public void delete(Long id){
        toppingRepository.deleteById(id);
    }
}
