package com.POG.julindang.cafe.service;


import com.POG.julindang.cafe.domain.Bookmark;
import com.POG.julindang.cafe.dto.request.BookMarkDeleteRequestDto;
import com.POG.julindang.cafe.dto.request.BookMarkSaveRequestDto;
import com.POG.julindang.cafe.dto.response.BookmarkDto;
import com.POG.julindang.cafe.repository.BookmarkRepository;
import com.POG.julindang.common.exception.cafe.BeverageNameDoesNotExist;
import com.POG.julindang.common.exception.cafe.CafeNameDoesNotExist;
import com.POG.julindang.common.exception.user.UserEmailDoesNotExist;
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
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;

    public BookmarkDto saveBookmark(BookMarkSaveRequestDto bookMarkSaveRequestDto){
        String cafeName = bookMarkSaveRequestDto.getCafeName();
        String beverageName = bookMarkSaveRequestDto.getBeverageName();
        String userEmail = bookMarkSaveRequestDto.getUserEmail();

        if(cafeName == null){
            throw new CafeNameDoesNotExist();
        }
        if(beverageName == null){
            throw new BeverageNameDoesNotExist();
        }
        if(userEmail == null){
            throw new UserEmailDoesNotExist();
        }
        Bookmark result = bookmarkRepository.save(bookMarkSaveRequestDto.toEntity());

        return new BookmarkDto(result);
    }

    public BookmarkDto toggleDeleted(String beverageName, String cafeName, String userEmail){
        if(beverageName == null){
            throw new BeverageNameDoesNotExist();
        }

        if(cafeName == null){
            throw new CafeNameDoesNotExist();
        }
        if(userEmail == null){
            throw new UserEmailDoesNotExist();
        }
        Bookmark result = bookmarkRepository.findByCafeNameAndBeverageNameAndUserEmail(cafeName, beverageName, userEmail);

        result.toggleDeleted();

        return BookmarkDto.builder()
                .userEmail(userEmail)
                .beverageName(beverageName)
                .cafeName(cafeName)
                .build();

    }

    public List<BookmarkDto> findAllByUserEmail(String userEmail){
        if(userEmail == null){
            throw new UserEmailDoesNotExist();
        }
        List<Bookmark> result = bookmarkRepository.findAllByUserEmail(userEmail);

        return result.stream()
                .filter(x-> x.getDeleted()==false)
                .map(BookmarkDto::new)
                .collect(Collectors.toList());
    }
}
