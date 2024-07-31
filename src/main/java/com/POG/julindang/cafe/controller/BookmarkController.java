package com.POG.julindang.cafe.controller;


import com.POG.julindang.cafe.dto.request.BookMarkSaveRequestDto;
import com.POG.julindang.cafe.dto.request.BookmarkDeleteRequestDto;
import com.POG.julindang.cafe.dto.response.bookmark.*;
import com.POG.julindang.cafe.service.BookmarkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cafe/bookmark")
@Tag(name = "bookmark")
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @Operation(description = "즐겨찾기 정보를 저장")
    @PostMapping("/save")
    public ResponseEntity<BookmarkResponseDto> saveBookmark(@RequestBody BookMarkSaveRequestDto bookMarkSaveRequestDto){

        return ResponseEntity.ok(bookmarkService.saveBookmark(bookMarkSaveRequestDto));
    }

    @Operation(description = "즐겨찾기 정보를 삭제")
    @PostMapping("/delete")
    public ResponseEntity<Void> deleteBookmark(@RequestBody BookmarkDeleteRequestDto bookmarkDeleteRequestDto) {
        bookmarkService.deleteBookmark(bookmarkDeleteRequestDto);
        return ResponseEntity.ok().build();
    }

    @Operation(description = "즐겨찾기 음료, 디저트전체 조회")
    @GetMapping("/all")
    public ResponseEntity <BookmarkAllResponseDto> getBookmark() {
        return ResponseEntity.ok(bookmarkService.getBookmark());
    }

    @Operation(description = "디저트 즐겨찾기 조회")
    @GetMapping("/dessert")
    public ResponseEntity <DessertBookmarkResponseDto> getDessertBookmark() {
        return ResponseEntity.ok(bookmarkService.getDessertBookmark());
    }

    @Operation(description = "음료 즐겨찾기 조회")
    @GetMapping("/beverage")
    public ResponseEntity <BeverageBookmarkResponseDto> getBeverageBookmark() {
        return ResponseEntity.ok(bookmarkService.getBeverageBookmark());
    }

}
