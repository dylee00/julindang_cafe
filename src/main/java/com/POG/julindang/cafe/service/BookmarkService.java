package com.POG.julindang.cafe.service;

import com.POG.julindang.cafe.dto.request.BookMarkSaveRequestDto;
import com.POG.julindang.cafe.dto.request.BookmarkDeleteRequestDto;
import com.POG.julindang.cafe.dto.response.bookmark.BookmarkResponseDto;

public interface BookmarkService {
    public BookmarkResponseDto saveBookmark(BookMarkSaveRequestDto bookMarkSaveRequestDto);

    public void deleteBookmark(BookmarkDeleteRequestDto bookmarkDeleteRequestDto);
}
