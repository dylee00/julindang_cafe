package com.POG.julindang.cafe.service;


import com.POG.julindang.cafe.domain.Bookmark;
import com.POG.julindang.cafe.dto.request.BookMarkSaveRequestDto;
import com.POG.julindang.cafe.dto.response.bookmark.BookmarkResponseDto;
import com.POG.julindang.cafe.dto.response.bookmark.BookmarkCafeNameResponseDto;
import com.POG.julindang.cafe.dto.response.bookmark.BookmarkProductNameResponseDto;
import com.POG.julindang.cafe.repository.BookmarkRepository;
import com.POG.julindang.common.exception.bookmark.BookMarkDoesNotExist;
import com.POG.julindang.common.exception.bookmark.ProductNameDoesNotExist;
import com.POG.julindang.common.exception.cafe.BeverageNameDoesNotExist;
import com.POG.julindang.common.exception.cafe.CafeNameDoesNotExist;
import com.POG.julindang.common.exception.user.UserEmailDoesNotExist;
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
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;

    public BookmarkResponseDto saveBookmark(BookMarkSaveRequestDto bookMarkSaveRequestDto){
        String cafeName = bookMarkSaveRequestDto.getCafeName();
        String productName = bookMarkSaveRequestDto.getProductName();
        String userEmail = bookMarkSaveRequestDto.getUserEmail();

        if(cafeName == null){
            throw new CafeNameDoesNotExist();
        }
        if(productName == null){
            throw new ProductNameDoesNotExist();
        }
        if(userEmail == null){
            throw new UserEmailDoesNotExist();
        }
        Bookmark result = bookmarkRepository.save(bookMarkSaveRequestDto.toEntity());

        return new BookmarkResponseDto(result);
    }

    public BookmarkResponseDto toggleDeleted(String productName, String cafeName, String userEmail){
        if(productName == null){
            throw new BeverageNameDoesNotExist();
        }
        if(cafeName == null){
            throw new CafeNameDoesNotExist();
        }
        if(userEmail == null){
            throw new UserEmailDoesNotExist();
        }

        Bookmark result = bookmarkRepository.findByCafeNameAndProductNameAndUserEmail(cafeName, productName, userEmail)
                .orElseThrow(()-> new BookMarkDoesNotExist(cafeName + " " + productName + " " + userEmail));

        result.toggleDeleted();

        return BookmarkResponseDto.builder()
                .userEmail(userEmail)
                .productName(productName)
                .cafeName(cafeName)
                .build();

    }

    public List<BookmarkResponseDto> findAllByUserEmail(String userEmail){
        if(userEmail == null){
            throw new UserEmailDoesNotExist();
        }
        List<Bookmark> result = bookmarkRepository.findAllByUserEmail(userEmail);

        return result.stream()
                .map(BookmarkResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<BookmarkCafeNameResponseDto> findBookmarkedCafeNamesByUserEmail(String userEmail){
        if(userEmail == null){
            throw new UserEmailDoesNotExist();
        }
        AtomicLong id = new AtomicLong(0);
        List<BookmarkCafeNameResponseDto> result = new ArrayList<>();
        List<String> cafeNames = bookmarkRepository.findDistinctCafeNameByUserEmail(userEmail);
        for (String cafeName : cafeNames) {
            BookmarkCafeNameResponseDto build = BookmarkCafeNameResponseDto.builder()
                    .id(id.getAndIncrement())
                    .cafeName(cafeName)
                    .build();

            result.add(build);
        }
        return result;
    }

    public List<BookmarkProductNameResponseDto> findBookmarkedProductNamesByCafeNameAndUserEmail(String cafeName, String userEmail){
        if(userEmail == null){
            throw new UserEmailDoesNotExist();
        }
        if(cafeName == null){
            throw new CafeNameDoesNotExist();
        }

        AtomicLong id = new AtomicLong(0);
        List<BookmarkProductNameResponseDto> result = new ArrayList<>();
        List<String> productNames = bookmarkRepository.findDistinctProductNameByUserEmailAndCafeName(userEmail, cafeName);

        for (String productName : productNames) {
            BookmarkProductNameResponseDto build = BookmarkProductNameResponseDto.builder()
                    .id(id.getAndIncrement())
                    .productName(productName)
                    .build();

            result.add(build);
        }

        return result;
    }
}
