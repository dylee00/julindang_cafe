package com.POG.julindang.cafe.service;


import com.POG.julindang.cafe.domain.BeverageBookmark;
import com.POG.julindang.cafe.domain.CafeBookmark;
import com.POG.julindang.cafe.domain.DessertBookmark;
import com.POG.julindang.cafe.dto.request.BookMarkSaveRequestDto;
import com.POG.julindang.cafe.dto.response.bookmark.BookmarkResponseDto;
import com.POG.julindang.cafe.repository.*;
import com.POG.julindang.cafe.util.JwtUtil;
import com.POG.julindang.common.exception.bookmark.BookMarkDoesNotExist;
import com.POG.julindang.common.exception.bookmark.BookmarkAlreadyExist;
import com.POG.julindang.common.exception.bookmark.InvalidBookmarkType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class BookmarkService {
    private final CafeBookmarkRepository cafeBookmarkRepository;
    private final BeverageBookmarkRepository beverageBookmarkRepository;
    private final DessertBookmarkRepository dessertBookmarkRepository;

    public BookmarkResponseDto saveBookmark(BookMarkSaveRequestDto bookMarkSaveRequestDto){

        String cafeName = bookMarkSaveRequestDto.getCafeName();
        Integer type = bookMarkSaveRequestDto.getType();

        switch (type){
            case 0:
                cafeBookmarkRepository.findByUserEmailAndCafeName(JwtUtil.getEmail(), cafeName)
                        .ifPresentOrElse(x -> {
                            if(x.getDeleted()){
                                x.toggleDeleted();
                                cafeBookmarkRepository.save(x);
                            }
                            else{
                                throw new BookmarkAlreadyExist("User Email : " + JwtUtil.getEmail() + " Cafe Name : " + cafeName);
                            }
                        }, ()->{
                            cafeBookmarkRepository.save(CafeBookmark.builder()
                                                .cafeName(cafeName)
                                                .deleted(false)
                                                .createdAt(LocalDateTime.now())
                                                .userEmail(JwtUtil.getEmail())
                                                .build());
                        });

                break;

            case 1:
                // 음료 즐찾 저장
                String beverageName = bookMarkSaveRequestDto.getProductName();
                beverageBookmarkRepository.findByUserEmailAndCafeNameAndBeverageName(JwtUtil.getEmail(), cafeName, beverageName)
                        .ifPresentOrElse(x -> {
                            if(x.getDeleted()){
                                x.toggleDeleted();
                                beverageBookmarkRepository.save(x);
                            }
                            else{
                                throw new BookmarkAlreadyExist("User Email : " + JwtUtil.getEmail() + " Cafe Name : " + cafeName + " Beverage Name : " + beverageName);
                            }
                        }, () ->{
                            beverageBookmarkRepository.save(BeverageBookmark.builder()
                                            .beverageName(beverageName)
                                            .cafeName(cafeName)
                                            .createdAt(LocalDateTime.now())
                                            .deleted(false)
                                            .userEmail(JwtUtil.getEmail())
                                            .build());
                        });

                break;

            case 2:
                String dessertName = bookMarkSaveRequestDto.getProductName();
                dessertBookmarkRepository.findByUserEmailAndCafeNameAndDessertName(JwtUtil.getEmail(), cafeName, dessertName)
                        .ifPresentOrElse(x -> {
                            if (x.getDeleted()) {
                                x.toggleDeleted();
                                dessertBookmarkRepository.save(x);
                            } else {
                                throw new BookmarkAlreadyExist("User Email : " + JwtUtil.getEmail() + " Cafe Name : " + cafeName + " Dessert Name : " + dessertName);
                            }
                        }, () -> {
                            dessertBookmarkRepository.save(DessertBookmark.builder()
                                    .dessertName(dessertName)
                                    .cafeName(cafeName)
                                    .createdAt(LocalDateTime.now())
                                    .deleted(false)
                                    .userEmail(JwtUtil.getEmail())
                                    .build());
                        });

                break;

            default:
                throw new InvalidBookmarkType(type);

        }
        return BookmarkResponseDto.builder()
                .cafeName(cafeName)
                .productName(bookMarkSaveRequestDto.getProductName())
                .build();
    }

    public BookmarkResponseDto delete(String productName, String cafeName, Integer type){
        switch (type){
            case 0:
                // 카페 즐찾
                cafeBookmarkRepository.findByUserEmailAndCafeName(JwtUtil.getEmail(), cafeName)
                                .ifPresentOrElse(x ->{
                                    if(x.getDeleted()){
                                        throw new BookMarkDoesNotExist("User Email : " + JwtUtil.getEmail() + " Cafe Name : " + cafeName);
                                    }
                                    else{
                                        x.toggleDeleted();
                                        cafeBookmarkRepository.save(x);
                                    }
                                },
                                        () -> {
                                            throw new BookMarkDoesNotExist("User Email : " + JwtUtil.getEmail() + " Cafe Name : " + cafeName);
                                        });

                break;

            case 1:
                // 음료 즐찾
                beverageBookmarkRepository.findByUserEmailAndCafeNameAndBeverageName(JwtUtil.getEmail(), cafeName, productName)
                                .ifPresentOrElse(x -> {
                                    if (x.getDeleted()) {
                                        throw new BookMarkDoesNotExist("User Email : " + JwtUtil.getEmail() + " Cafe Name : " + cafeName + " Beverage Name : " + productName);
                                    }
                                    else{
                                        x.toggleDeleted();
                                        beverageBookmarkRepository.save(x);
                                    }
                                },
                                        ()-> {
                                    throw new BookMarkDoesNotExist("User Email : " + JwtUtil.getEmail() + " Cafe Name : " + cafeName + " Beverage Name : " + productName);
                                });


                break;

            case 2:
                // 디저트 즐찾
                dessertBookmarkRepository.findByUserEmailAndCafeNameAndDessertName(JwtUtil.getEmail(), cafeName, productName)
                                .ifPresentOrElse(x -> {
                                    if(x.getDeleted()){
                                        throw new BookMarkDoesNotExist("User Email : " + JwtUtil.getEmail() + " Cafe Name : " + cafeName + "Dessert Name : " + productName);
                                    }
                                    else{
                                        x.toggleDeleted();
                                        dessertBookmarkRepository.save(x);
                                    }
                                },
                                        () -> {
                                    throw new BookMarkDoesNotExist("User Email : " + JwtUtil.getEmail() + " Cafe Name : " + cafeName + "Dessert Name : " + productName);
                                });

                break;

            default:
                throw new InvalidBookmarkType(type);

        }


        return BookmarkResponseDto.builder()
                .productName(productName)
                .cafeName(cafeName)
                .build();

    }

}
