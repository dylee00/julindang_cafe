package com.POG.julindang.cafe.controller;


import com.POG.julindang.cafe.dto.request.BookMarkSaveRequestDto;
import com.POG.julindang.cafe.dto.response.bookmark.BookmarkResponseDto;
import com.POG.julindang.cafe.dto.response.bookmark.BookmarkCafeNameResponseDto;
import com.POG.julindang.cafe.dto.response.bookmark.BookmarkProductNameResponseDto;
import com.POG.julindang.cafe.service.BookmarkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cafe/bookmark")
@Tag(name = "bookmark")
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @Operation(summary = "즐겨찾기 전체 탐색",
            description = "모든 즐겨찾기 정보를 상용자 이메일을 통해 로드")
    @GetMapping
    public ResponseEntity<List<BookmarkResponseDto>> findAll(@RequestParam(name="email", required = false) String email){
        return new ResponseEntity(bookmarkService.findAllByUserEmail(email), HttpStatus.OK);
    }
    @Operation(summary = "즐겨찾기 삭제",
            description = "해당 즐겨찾기 정보를 삭제(토글)")
    @DeleteMapping("/delete")
    public ResponseEntity<BookmarkResponseDto> toggleDeleted(@RequestParam(name="beverageName", required = false) String beverageName,
                                                             @RequestParam(name="cafeName", required = false) String cafeName,
                                                             @RequestParam(name="userEmail", required = false) String userEmail){
        return new ResponseEntity<>(bookmarkService.toggleDeleted(beverageName, cafeName, userEmail), HttpStatus.OK);
    }

    @Operation(summary = "즐겨찾기 저장",
            description = "즐겨찾기 정보를 저장")
    @PostMapping("/save")
    public ResponseEntity<BookmarkResponseDto> saveBookmark(@RequestBody BookMarkSaveRequestDto bookMarkSaveRequestDto){
        return new ResponseEntity<>(bookmarkService.saveBookmark(bookMarkSaveRequestDto), HttpStatus.CREATED);
    }
    @Operation(summary = "즐겨찾기된 카페 이름들 로드",
            description = "즐겨찾기된 카페 이름들을 로딩")
    @GetMapping("/find-cafe-names")
    public ResponseEntity<List<BookmarkCafeNameResponseDto>> findBookmarkedCafeNamesByUserEmail(@RequestParam(name="userEmail", required = false) String userEmail){
        return new ResponseEntity<>(bookmarkService.findBookmarkedCafeNamesByUserEmail(userEmail), HttpStatus.OK);
    }

    @Operation(summary = "즐겨찾기된 제품 이름들 로드",
            description = "즐겨찾기된 제품 이름들을 로딩")
    @GetMapping("/find-product-names")
    public ResponseEntity<List<BookmarkProductNameResponseDto>> findBookmarkedProductNamesByCafeNameAndUserEmail(@RequestParam(name="userEmail", required = false) String userEmail,
                                                                                                                 @RequestParam(name = "cafeName", required = false) String cafeName){
        return new ResponseEntity<>(bookmarkService.findBookmarkedProductNamesByCafeNameAndUserEmail(cafeName, userEmail), HttpStatus.OK);
    }
}
