package com.POG.julindang.cafe.service;


import com.POG.julindang.cafe.domain.BeverageBookmark;
import com.POG.julindang.cafe.domain.CafeBookmark;
import com.POG.julindang.cafe.domain.DessertBookmark;
import com.POG.julindang.cafe.domain.Member;
import com.POG.julindang.cafe.dto.request.BookMarkSaveRequestDto;
import com.POG.julindang.cafe.dto.response.bookmark.BookmarkResponseDto;
import com.POG.julindang.cafe.repository.*;
import com.POG.julindang.common.exception.bookmark.BookMarkDoesNotExist;
import com.POG.julindang.common.exception.bookmark.BookmarkAlreadyExist;
import com.POG.julindang.common.exception.bookmark.BookmarkTypeDoesNotExist;
import com.POG.julindang.common.exception.bookmark.InvalidBookmarkType;
import com.POG.julindang.common.exception.cafe.BeverageNameDoesNotExist;
import com.POG.julindang.common.exception.cafe.CafeDoesNotExist;
import com.POG.julindang.common.exception.cafe.CafeNameDoesNotExist;
import com.POG.julindang.common.exception.dessert.DessertNameDoesNotExist;
import com.POG.julindang.common.exception.user.AccountNotFound;
import com.POG.julindang.common.exception.user.UserEmailDoesNotExist;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;



@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class BookmarkService {
    private final CafeBookmarkRepository cafeBookmarkRepository;
    private final BeverageBookmarkRepository beverageBookmarkRepository;
    private final DessertBookmarkRepository dessertBookmarkRepository;
    private final MemberRepository memberRepository;

    public BookmarkResponseDto saveBookmark(BookMarkSaveRequestDto bookMarkSaveRequestDto){

        String cafeName = bookMarkSaveRequestDto.getCafeName();
        String userEmail = bookMarkSaveRequestDto.getUserEmail();
        Integer type = bookMarkSaveRequestDto.getType();

        Member member = memberRepository.findByEmail(userEmail)
                .orElseThrow(() -> new AccountNotFound(userEmail));

        switch (type){
            case 0:
                cafeBookmarkRepository.findByUserEmailAndCafeName(userEmail, cafeName)
                        .ifPresentOrElse(x -> {
                            if(x.getDeleted()){
                                x.toggleDeleted();
                                cafeBookmarkRepository.save(x);
                            }
                            else{
                                throw new BookmarkAlreadyExist("User Email : " + userEmail + " Cafe Name : " + cafeName);
                            }
                        }, ()->{
                            cafeBookmarkRepository.save(CafeBookmark.builder()
                                                .cafeName(cafeName)
                                                .deleted(false)
                                                .createdAt(LocalDateTime.now())
                                                .member(member)
                                                .userEmail(userEmail)
                                                .build());
                        });

                break;

            case 1:
                // 음료 즐찾 저장
                String beverageName = bookMarkSaveRequestDto.getProductName();
                beverageBookmarkRepository.findByUserEmailAndCafeNameAndBeverageName(userEmail, cafeName, beverageName)
                        .ifPresentOrElse(x -> {
                            if(x.getDeleted()){
                                x.toggleDeleted();
                                beverageBookmarkRepository.save(x);
                            }
                            else{
                                throw new BookmarkAlreadyExist("User Email : " + userEmail + " Cafe Name : " + cafeName + " Beverage Name : " + beverageName);
                            }
                        }, () ->{
                            beverageBookmarkRepository.save(BeverageBookmark.builder()
                                            .beverageName(beverageName)
                                            .cafeName(cafeName)
                                            .member(member)
                                            .createdAt(LocalDateTime.now())
                                            .deleted(false)
                                            .userEmail(userEmail)
                                            .build());
                        });

                break;

            case 2:
                String dessertName = bookMarkSaveRequestDto.getProductName();
                dessertBookmarkRepository.findByUserEmailAndCafeNameAndDessertName(userEmail, cafeName, dessertName)
                        .ifPresentOrElse(x -> {
                            if (x.getDeleted()) {
                                x.toggleDeleted();
                                dessertBookmarkRepository.save(x);
                            } else {
                                throw new BookmarkAlreadyExist("User Email : " + userEmail + " Cafe Name : " + cafeName + " Dessert Name : " + dessertName);
                            }
                        }, () -> {
                            dessertBookmarkRepository.save(DessertBookmark.builder()
                                    .dessertName(dessertName)
                                    .cafeName(cafeName)
                                    .member(member)
                                    .createdAt(LocalDateTime.now())
                                    .deleted(false)
                                    .userEmail(userEmail)
                                    .build());
                        });

                break;

            default:
                throw new InvalidBookmarkType(type);

        }
        return BookmarkResponseDto.builder()
                .cafeName(cafeName)
                .productName(bookMarkSaveRequestDto.getProductName())
                .userEmail(userEmail)
                .build();
    }

    public BookmarkResponseDto delete(String productName, String cafeName, String userEmail, Integer type){
        switch (type){
            case 0:
                // 카페 즐찾
                cafeBookmarkRepository.findByUserEmailAndCafeName(userEmail, cafeName)
                                .ifPresentOrElse(x ->{
                                    if(x.getDeleted()){
                                        throw new BookMarkDoesNotExist("User Email : " + userEmail + " Cafe Name : " + cafeName);
                                    }
                                    else{
                                        x.toggleDeleted();
                                        cafeBookmarkRepository.save(x);
                                    }
                                },
                                        () -> {
                                            throw new BookMarkDoesNotExist("User Email : " + userEmail + " Cafe Name : " + cafeName);
                                        });

                break;

            case 1:
                // 음료 즐찾
                beverageBookmarkRepository.findByUserEmailAndCafeNameAndBeverageName(userEmail, cafeName, productName)
                                .ifPresentOrElse(x -> {
                                    if (x.getDeleted()) {
                                        throw new BookMarkDoesNotExist("User Email : " + userEmail + " Cafe Name : " + cafeName + " Beverage Name : " + productName);
                                    }
                                    else{
                                        x.toggleDeleted();
                                        beverageBookmarkRepository.save(x);
                                    }
                                },
                                        ()-> {
                                    throw new BookMarkDoesNotExist("User Email : " + userEmail + " Cafe Name : " + cafeName + " Beverage Name : " + productName);
                                });


                break;

            case 2:
                // 디저트 즐찾
                dessertBookmarkRepository.findByUserEmailAndCafeNameAndDessertName(userEmail, cafeName, productName)
                                .ifPresentOrElse(x -> {
                                    if(x.getDeleted()){
                                        throw new BookMarkDoesNotExist("User Email : " + userEmail + " Cafe Name : " + cafeName + "Dessert Name : " + productName);
                                    }
                                    else{
                                        x.toggleDeleted();
                                        dessertBookmarkRepository.save(x);
                                    }
                                },
                                        () -> {
                                    throw new BookMarkDoesNotExist("User Email : " + userEmail + " Cafe Name : " + cafeName + "Dessert Name : " + productName);
                                });

                break;

            default:
                throw new InvalidBookmarkType(type);

        }


        return BookmarkResponseDto.builder()
                .userEmail(userEmail)
                .productName(productName)
                .cafeName(cafeName)
                .build();

    }

}
