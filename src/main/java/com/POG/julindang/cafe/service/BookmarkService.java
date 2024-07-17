package com.POG.julindang.cafe.service;

import com.POG.julindang.cafe.dto.request.BookMarkSaveRequestDto;
import com.POG.julindang.cafe.dto.request.BookmarkDeleteRequestDto;
import com.POG.julindang.cafe.dto.response.bookmark.*;

import java.util.List;

public interface BookmarkService {
    public BookmarkResponseDto saveBookmark(BookMarkSaveRequestDto bookMarkSaveRequestDto);

    public void deleteBookmark(BookmarkDeleteRequestDto bookmarkDeleteRequestDto);

    public DessertBookmarkResponseDto getDessertBookmark();

    public BookmarkAllResponseDto getBookmark();

    public BeverageBookmarkResponseDto getBeverageBookmark();
}
