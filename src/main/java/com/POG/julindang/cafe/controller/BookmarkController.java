package com.POG.julindang.cafe.controller;


import com.POG.julindang.cafe.dto.request.BookMarkSaveRequestDto;
import com.POG.julindang.cafe.dto.response.bookmark.BookmarkResponseDto;
import com.POG.julindang.cafe.dto.response.bookmark.BookmarkCafeNameResponseDto;
import com.POG.julindang.cafe.dto.response.bookmark.BookmarkProductNameResponseDto;
import com.POG.julindang.cafe.service.BookmarkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
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
    @Operation(description = "해당 즐겨찾기 정보를 삭제(토글)")
    @DeleteMapping("/delete")
    @Parameters({
                    @Parameter(name = "productName", description = "제품 이름"),
                    @Parameter(name="cafeName", description = "카페 이름"),
                    @Parameter(name="type", description = "즐겨찾기 타입 [ 0 : 카페 브렌드 즐겨찾기(이때는 productName 불필요), 1 : 음료 즐겨찾기 (모든 파라미터 필요), 2 : 디저트 즐겨찾기 (모든 파라미터 필요) ]")
            })
    public ResponseEntity<BookmarkResponseDto> toggleDeleted(@RequestParam(name="productName", required = false) String productName,
                                                             @RequestParam(name="cafeName", required = false) String cafeName,
                                                             @RequestParam(name="type", required = false) Integer type){
        return new ResponseEntity<>(bookmarkService.delete(productName, cafeName, type), HttpStatus.OK);
    }
    @Operation(description = "즐겨찾기 정보를 저장")
    @PostMapping("/save")
    public ResponseEntity<BookmarkResponseDto> saveBookmark(@RequestBody BookMarkSaveRequestDto bookMarkSaveRequestDto){
        return new ResponseEntity<>(bookmarkService.saveBookmark(bookMarkSaveRequestDto), HttpStatus.CREATED);
    }
}
