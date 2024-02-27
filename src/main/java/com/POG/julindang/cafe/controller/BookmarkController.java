package com.POG.julindang.cafe.controller;


import com.POG.julindang.cafe.dto.request.BookMarkDeleteRequestDto;
import com.POG.julindang.cafe.dto.request.BookMarkSaveRequestDto;
import com.POG.julindang.cafe.dto.response.BookmarkDto;
import com.POG.julindang.cafe.service.BookmarkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/bookmark")
@Tag(name = "즐겨찾기 관련 컨트롤러")
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @Operation(summary = "즐겨찾기 전체 탐색",
            description = "모든 즐겨찾기 정보를 상용자 이메일을 통해 로드")
    @GetMapping("/find-all")
    public ResponseEntity<List<BookmarkDto>> findAll(@RequestParam(name="email", required = false) String email){
        return new ResponseEntity(bookmarkService.findAllByUserEmail(email), HttpStatus.OK);
    }
    @Operation(summary = "즐겨찾기 삭제",
            description = "해당 즐겨찾기 정보를 삭제(토글)")
    @DeleteMapping("/delete")
    public ResponseEntity<BookmarkDto> toggleDeleted(@RequestBody BookMarkDeleteRequestDto bookmarkDto){
        return new ResponseEntity<>(bookmarkService.toggleDeleted(bookmarkDto), HttpStatus.OK);
    }

    @Operation(summary = "즐겨찾기 저장",
            description = "즐겨찾기 정보를 저장")
    @PostMapping("/save")
    public ResponseEntity<BookmarkDto> saveBookmark(@RequestBody BookMarkSaveRequestDto bookMarkSaveRequestDto){
        return new ResponseEntity<>(bookmarkService.saveBookmark(bookMarkSaveRequestDto), HttpStatus.CREATED);
    }
}
