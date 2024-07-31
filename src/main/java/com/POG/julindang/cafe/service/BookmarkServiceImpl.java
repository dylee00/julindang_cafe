package com.POG.julindang.cafe.service;


import com.POG.julindang.cafe.domain.BeverageBookmark;
import com.POG.julindang.cafe.domain.CafeBookmark;
import com.POG.julindang.cafe.domain.DessertBookmark;
import com.POG.julindang.cafe.dto.request.BookMarkSaveRequestDto;
import com.POG.julindang.cafe.dto.request.BookmarkDeleteRequestDto;
import com.POG.julindang.cafe.dto.response.bookmark.*;
import com.POG.julindang.cafe.repository.*;
import com.POG.julindang.cafe.util.JwtUtil;
import com.POG.julindang.cafe.vo.BeverageBookmarkVo;
import com.POG.julindang.cafe.vo.DessertBookmarkVo;
import com.POG.julindang.cafe.vo.FreeConsumeBookmarkVo;
import com.POG.julindang.common.exception.bookmark.BookMarkDoesNotExist;
import com.POG.julindang.common.exception.bookmark.BookmarkTypeDoesNotExist;
import com.POG.julindang.common.exception.bookmark.InvalidBookmarkType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {
    private final CafeBookmarkRepository cafeBookmarkRepository;
    private final BeverageBookmarkRepository beverageBookmarkRepository;
    private final DessertBookmarkRepository dessertBookmarkRepository;

    @Override
    @Transactional
    public BookmarkResponseDto saveBookmark(BookMarkSaveRequestDto bookMarkSaveRequestDto) {

        String cafeName = bookMarkSaveRequestDto.getCafeName();
        Integer type = bookMarkSaveRequestDto.getType();

        if (type == null || type > 3) {
            throw new BookmarkTypeDoesNotExist("Type parameter is required");
        }

        String userEmail = JwtUtil.getEmail();
        Long memberId = JwtUtil.getMemberId();

        switch (type) {
            case 0:
                CafeBookmark cafeBookmark = CafeBookmark.builder()
                        .cafeName(cafeName)
                        .deleted(false)
                        .memberId(memberId)
                        .userEmail(userEmail)
                        .build();
                cafeBookmarkRepository.save(cafeBookmark);
                break;

            case 1:
                String beverageName = bookMarkSaveRequestDto.getProductName();
                BeverageBookmark beverageBookmark = BeverageBookmark.builder()
                        .cafeName(cafeName)
                        .beverageName(beverageName)
                        .deleted(false)
                        .memberId(memberId)
                        .userEmail(userEmail)
                        .build();
                beverageBookmarkRepository.save(beverageBookmark);
                break;

            case 2:
                String dessertName = bookMarkSaveRequestDto.getProductName();
                DessertBookmark dessertBookmark = DessertBookmark.builder()
                        .cafeName(cafeName)
                        .dessertName(dessertName)
                        .deleted(false)
                        .memberId(memberId)
                        .userEmail(userEmail)
                        .build();
                dessertBookmarkRepository.save(dessertBookmark);
                break;

            default:
                throw new InvalidBookmarkType(type);
        }

        return BookmarkResponseDto.builder()
                .cafeName(cafeName)
                .productName(bookMarkSaveRequestDto.getProductName())
                .build();
    }
    @Override
    @Transactional
    public void deleteBookmark(BookmarkDeleteRequestDto bookmarkDeleteRequestDto) {
        String cafeName = bookmarkDeleteRequestDto.getCafeName();
        Integer type = bookmarkDeleteRequestDto.getType();

        if (type == null || type > 3) {
            throw new BookmarkTypeDoesNotExist("Type parameter is required");
        }

        switch (type) {
            case 0:
                // 카페 브랜드 즐겨찾기 삭제
                CafeBookmark cafeBookmark = cafeBookmarkRepository.findByUserEmailAndCafeName(JwtUtil.getEmail(), cafeName)
                        .orElseThrow(() -> new BookMarkDoesNotExist("cafe brand Bookmark is not found"));
                cafeBookmark.setDeleted(true);
                cafeBookmarkRepository.save(cafeBookmark);
                break;

            case 1:
                // 음료 즐겨찾기 삭제
                String beverageName = bookmarkDeleteRequestDto.getProductName();
                BeverageBookmark beverageBookmark = beverageBookmarkRepository.findByUserEmailAndCafeNameAndBeverageName(JwtUtil.getEmail(), cafeName, beverageName)
                        .orElseThrow(() -> new BookMarkDoesNotExist("beverage Bookmark is not found"));
                beverageBookmark.setDeleted(true);
                beverageBookmarkRepository.save(beverageBookmark);
                break;

            case 2:
                // 디저트 즐겨찾기 삭제
                String dessertName = bookmarkDeleteRequestDto.getProductName();
                DessertBookmark dessertBookmark = dessertBookmarkRepository.findByUserEmailAndCafeNameAndDessertName(JwtUtil.getEmail(), cafeName, dessertName)
                        .orElseThrow(() -> new BookMarkDoesNotExist("dessert Bookmark is not found"));
                dessertBookmark.setDeleted(true);
                dessertBookmarkRepository.save(dessertBookmark);
                break;

            default:
                throw new InvalidBookmarkType(type);
        }
    }

    //전체 즐겨찾기 조회


    @Override
    @Transactional(readOnly = true)
    public BookmarkAllResponseDto getBookmark() {
        List<BeverageBookmarkVo> bvos = beverageBookmarkRepository.findBeverageBookmarkByMemberId(JwtUtil.getMemberId());
        List<CafeBeverageBookmarkResponseDto> cafes = new ArrayList<>();
        for(BeverageBookmarkVo beverageBookmarkVo : bvos) {
            cafes.add(CafeBeverageBookmarkResponseDto.builder()
                            .cafeName(beverageBookmarkVo.getCafeName())
                            .beverageName(beverageBookmarkVo.getBeverageName())
                            .sugar(beverageBookmarkVo.getSugar())
                            .calorie(beverageBookmarkVo.getCalorie())
                            .createdAt(beverageBookmarkVo.getCreatedAt())
                            .cafeId(beverageBookmarkVo.getCafeId())
                    .build());
        }

        List<DessertBookmarkVo> dvos = dessertBookmarkRepository.findDessertBookmarkByMemberId(JwtUtil.getMemberId());
        List<CafeDessertBookmarkResponseDto> desserts = new ArrayList<>();
        for(DessertBookmarkVo dessertBookmarkVo : dvos){
            desserts.add(CafeDessertBookmarkResponseDto.builder()
                    .cafeName(dessertBookmarkVo.getCafeName())
                    .dessertName(dessertBookmarkVo.getDessertName())
                    .sugar(dessertBookmarkVo.getSugar())
                    .calorie(dessertBookmarkVo.getCalorie())
                    .createdAt(dessertBookmarkVo.getCreatedAt())
                    .dessertId(dessertBookmarkVo.getDessertId())
                    .build());
        }

        List<FreeConsumeBookmarkVo> freevos = beverageBookmarkRepository.findFreeConsumeBookmarkByMemberId(JwtUtil.getMemberId());
        List<FreeConsumeBookmarkResponseDto> freedtos = new ArrayList<>();
        for(FreeConsumeBookmarkVo freeConsumeBookmarkVo : freevos){
            freedtos.add(FreeConsumeBookmarkResponseDto.builder()
                    .name(freeConsumeBookmarkVo.getName())
                    .sugar(freeConsumeBookmarkVo.getSugar())
                    .calorie(freeConsumeBookmarkVo.getCalorie())
                    .createdAt(freeConsumeBookmarkVo.getCreatedAt())
                    .consumeId(freeConsumeBookmarkVo.getConsumeId())
                    .productId(freeConsumeBookmarkVo.getProductId())
                    .type(freeConsumeBookmarkVo.getType())
                    .build());
        }


        return BookmarkAllResponseDto.builder()
                .cafeBeverages(cafes)
                .cafeDesserts(desserts)
                .freeConsumeBookmark(freedtos)
                .build();
    }

    //디저트 즐겨찾기 조회
    @Override
    @Transactional(readOnly = true)
    public DessertBookmarkResponseDto getDessertBookmark() {

        List<DessertBookmarkVo> vos = dessertBookmarkRepository.findDessertBookmarkByMemberId(JwtUtil.getMemberId());
        List<CafeDessertBookmarkResponseDto> dtos = new ArrayList<>();
        for(DessertBookmarkVo dessertBookmarkVo : vos){
            dtos.add(CafeDessertBookmarkResponseDto.builder()
                            .cafeName(dessertBookmarkVo.getCafeName())
                            .dessertName(dessertBookmarkVo.getDessertName())
                            .sugar(dessertBookmarkVo.getSugar())
                            .calorie(dessertBookmarkVo.getCalorie())
                            .createdAt(dessertBookmarkVo.getCreatedAt())
                            .dessertId(dessertBookmarkVo.getDessertId())
                    .build());
        }

        List<FreeConsumeBookmarkVo> freevos = dessertBookmarkRepository.findFreeConsumeDessertBookmarkByMemberId(JwtUtil.getMemberId());
        List<FreeConsumeBookmarkResponseDto> freedtos = new ArrayList<>();
        for(FreeConsumeBookmarkVo freeConsumeBookmarkVo : freevos){
            freedtos.add(FreeConsumeBookmarkResponseDto.builder()
                            .name(freeConsumeBookmarkVo.getName())
                            .sugar(freeConsumeBookmarkVo.getSugar())
                            .calorie(freeConsumeBookmarkVo.getCalorie())
                            .createdAt(freeConsumeBookmarkVo.getCreatedAt())
                            .consumeId(freeConsumeBookmarkVo.getConsumeId())
                            .productId(freeConsumeBookmarkVo.getProductId())
                            .type(freeConsumeBookmarkVo.getType())
                    .build());
        }


        return DessertBookmarkResponseDto.builder()
                .cafeDesserts(dtos)
                .freeConsumeDesserts(freedtos)
                .build();
    }


    //음료수 즐겨찾기 조회

    @Override
    @Transactional(readOnly = true)
    public BeverageBookmarkResponseDto getBeverageBookmark() {

        List<BeverageBookmarkVo> bvos = beverageBookmarkRepository.findBeverageBookmarkByMemberId(JwtUtil.getMemberId());
        List<CafeBeverageBookmarkResponseDto> cafes = new ArrayList<>();
        for(BeverageBookmarkVo beverageBookmarkVo : bvos) {
            cafes.add(CafeBeverageBookmarkResponseDto.builder()
                    .cafeName(beverageBookmarkVo.getCafeName())
                    .beverageName(beverageBookmarkVo.getBeverageName())
                    .sugar(beverageBookmarkVo.getSugar())
                    .calorie(beverageBookmarkVo.getCalorie())
                    .createdAt(beverageBookmarkVo.getCreatedAt())
                    .cafeId(beverageBookmarkVo.getCafeId())
                    .build());
        }

        List<FreeConsumeBookmarkVo> freevos = beverageBookmarkRepository.findFreeConsumeBeverageBookmarkByMemberId(JwtUtil.getMemberId());
        List<FreeConsumeBookmarkResponseDto> freedtos = new ArrayList<>();
        for(FreeConsumeBookmarkVo freeConsumeBookmarkVo : freevos){
            freedtos.add(FreeConsumeBookmarkResponseDto.builder()
                    .name(freeConsumeBookmarkVo.getName())
                    .sugar(freeConsumeBookmarkVo.getSugar())
                    .calorie(freeConsumeBookmarkVo.getCalorie())
                    .createdAt(freeConsumeBookmarkVo.getCreatedAt())
                    .consumeId(freeConsumeBookmarkVo.getConsumeId())
                    .productId(freeConsumeBookmarkVo.getProductId())
                    .type(freeConsumeBookmarkVo.getType())
                    .build());
        }

        return BeverageBookmarkResponseDto.builder()
                .cafeBeverages(cafes)
                .freeConsumeBeverages(freedtos)
                .build();
    }
}
