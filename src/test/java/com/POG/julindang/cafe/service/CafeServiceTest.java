package com.POG.julindang.cafe.service;


import com.POG.julindang.cafe.domain.Cafe;
import com.POG.julindang.cafe.dto.CafeDto;
import com.POG.julindang.cafe.repository.CafeRepository;
import com.POG.julindang.common.exception.CustomException;
import com.POG.julindang.common.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class CafeServiceTest {

    @Autowired
    private CafeService cafeService;
    @Autowired
    private CafeRepository cafeRepository;

    @AfterEach
    public void clear(){
        cafeService.clear();
    }

    @Test
    public void findAllIfDataExist() throws CustomException {
        //given
        CafeDto cafe1 = CafeDto.builder()
                .cafeName("스타벅스")
                .beverageName("아이스 아메리카노")
                .size("tall")
                .serve(355.0)
                .calorie(12.0)
                .temperature(false)
                .sugar(0.0)
                .build();


        CafeDto cafe2 = CafeDto.builder()
                .cafeName("스타벅스")
                .beverageName("카페라떼")
                .size("tall")
                .serve(355.0)
                .calorie(12.0)
                .temperature(false)
                .sugar(12.4)
                .build();

        CafeDto cafe3 = CafeDto.builder()
                .cafeName("투썸플레이스")
                .beverageName("아이스 아메리카노")
                .size("tall")
                .serve(355.0)
                .calorie(12.0)
                .temperature(false)
                .sugar(0.0)
                .build();

        //when
        cafeService.save(cafe1);
        cafeService.save(cafe2);
        cafeService.save(cafe3);
        List<Cafe> result = cafeRepository.findAll();
        //then

        assertThat(result.size()).isEqualTo(3);   // 저장 된 데이터들의 사이즈가 3개가 맞는지 비교

    }

    @Test
    public void findAllIfDataDoesNotExist(){
        //given

        //when
        List<Cafe> result = cafeRepository.findAll();   // 저장 된 정보가 없을때

        //then
        assertThat(result.size()).isEqualTo(0);
    }

    @Test
    public void findByCafeName() throws CustomException {
        //given
        CafeDto cafe1 = CafeDto.builder()
                .cafeName("스타벅스")
                .beverageName("아이스 아메리카노")
                .size("tall")
                .serve(355.0)
                .calorie(12.0)

                .temperature(false)
                .sugar(0.0)
                .build();


        CafeDto cafe2 = CafeDto.builder()
                .cafeName("스타벅스")
                .beverageName("카페라떼")
                .size("tall")
                .serve(355.0)
                .calorie(12.0)
                .temperature(false)
                .sugar(12.4)
                .build();

        CafeDto cafe3 = CafeDto.builder()
                .cafeName("투썸플레이스")
                .beverageName("아이스 아메리카노")
                .size("tall")
                .serve(355.0)
                .calorie(12.0)
                .temperature(false)
                .sugar(0.0)
                .build();

        //when
        cafeService.save(cafe1);
        cafeService.save(cafe2);
        cafeService.save(cafe3);
        List<Cafe> result = cafeRepository.findByCafeName("스타벅스");
        //then
        assertThat(result.size()).isEqualTo(2);

    }
    @Test
    public void findByCafeNameIfDoesNotMatch() throws CustomException {
        //given
        CafeDto cafe1 = CafeDto.builder()
                .cafeName("스타벅스")
                .beverageName("아이스 아메리카노")
                .size("tall")
                .serve(355.0)
                .calorie(12.0)
                .temperature(false)
                .sugar(0.0)
                .build();


        CafeDto cafe2 = CafeDto.builder()
                .cafeName("스타벅스")
                .beverageName("카페라떼")
                .size("tall")
                .serve(355.0)
                .calorie(12.0)
                .temperature(false)
                .sugar(12.4)
                .build();

        CafeDto cafe3 = CafeDto.builder()
                .cafeName("투썸플레이스")
                .beverageName("아이스 아메리카노")
                .size("tall")
                .serve(355.0)
                .calorie(12.0)
                .temperature(false)
                .sugar(0.0)
                .build();
        //when
        cafeService.save(cafe1);
        cafeService.save(cafe2);
        cafeService.save(cafe3);
        List<Cafe> result = cafeRepository.findByCafeName("별다방");
        //then
        assertThat(result.size()).isEqualTo(0);

    }

    @Test
    public void findByBeverageName() throws CustomException {
        //given
        CafeDto cafe1 = CafeDto.builder()
                .cafeName("스타벅스")
                .beverageName("아이스 아메리카노")
                .size("tall")
                .serve(355.0)
                .calorie(12.0)
                .temperature(false)
                .sugar(0.0)
                .build();


        CafeDto cafe2 = CafeDto.builder()
                .cafeName("스타벅스")
                .beverageName("카페라떼")
                .size("tall")
                .serve(355.0)
                .calorie(12.0)
                .temperature(false)
                .sugar(12.4)
                .build();

        CafeDto cafe3 = CafeDto.builder()
                .cafeName("투썸플레이스")
                .beverageName("아이스 아메리카노")
                .size("tall")
                .serve(355.0)
                .calorie(12.0)
                .temperature(false)
                .sugar(0.0)
                .build();

        //when
        cafeService.save(cafe1);
        cafeService.save(cafe2);
        cafeService.save(cafe3);
        List<Cafe> result = cafeRepository.findByBeverageName("아이스 아메리카노");
        //then
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void findByBeverageNameIfDoesNotMatch() throws CustomException {
        //given
        CafeDto cafe1 = CafeDto.builder()
                .cafeName("스타벅스")
                .beverageName("아이스 아메리카노")
                .size("tall")
                .serve(355.0)
                .calorie(12.0)
                .temperature(false)
                .sugar(0.0)
                .build();


        CafeDto cafe2 = CafeDto.builder()
                .cafeName("스타벅스")
                .beverageName("카페라떼")
                .size("tall")
                .serve(355.0)
                .calorie(12.0)
                .temperature(false)
                .sugar(12.4)
                .build();

        CafeDto cafe3 = CafeDto.builder()
                .cafeName("투썸플레이스")
                .beverageName("아이스 아메리카노")
                .size("tall")
                .serve(355.0)
                .calorie(12.0)
                .temperature(false)
                .sugar(0.0)
                .build();

        //when
        cafeService.save(cafe1);
        cafeService.save(cafe2);
        cafeService.save(cafe3);
        List<Cafe> result = cafeRepository.findByBeverageName("커피");

        //then
        assertThat(result.size()).isEqualTo(0);
    }

    @Test
    public void findCafeNameAndBeverageName() throws CustomException {
        //given
        CafeDto cafe1 = CafeDto.builder()
                .cafeName("스타벅스")
                .beverageName("아이스 아메리카노")
                .size("tall")
                .serve(355.0)
                .calorie(12.0)
                .temperature(false)
                .sugar(0.0)
                .build();


        CafeDto cafe2 = CafeDto.builder()
                .cafeName("스타벅스")
                .beverageName("카페라떼")
                .size("tall")
                .serve(355.0)
                .calorie(12.0)
                .temperature(false)
                .sugar(12.4)
                .build();

        CafeDto cafe3 = CafeDto.builder()
                .cafeName("투썸플레이스")
                .beverageName("아이스 아메리카노")
                .size("tall")
                .serve(355.0)
                .calorie(12.0)
                .temperature(false)
                .sugar(0.0)
                .build();

        CafeDto cafe4 = CafeDto.builder()
                .cafeName("투썸플레이스")
                .beverageName("카페 라떼")
                .size("tall")
                .serve(355.0)
                .calorie(60.0)
                .temperature(false)
                .sugar(0.0)
                .build();
        //when
        cafeService.save(cafe1);
        cafeService.save(cafe2);
        cafeService.save(cafe3);
        cafeService.save(cafe4);
        Optional<Cafe> findCafe1 = cafeRepository.findByCafeNameAndBeverageName("스타벅스", "아이스 아메리카노");
        Optional<Cafe> findCafe2 = cafeRepository.findByCafeNameAndBeverageName("스타벅스", "이런커피는 없다");
        Cafe findCafeIfCorrect = findCafe1.orElse(null);
        Cafe findCafeIfDoesNotCorrect = findCafe2.orElse(null);

        //then
        assertThat(findCafeIfCorrect.getCafeName()).isEqualTo(cafe1.getCafeName());
        assertThat(findCafeIfCorrect.getBeverageName()).isEqualTo(cafe1.getBeverageName()); // 찾은게 맞으면 리턴됨

        assertThat(findCafeIfDoesNotCorrect).isNull(); // 찾은게 없으면 empty

    }
}

