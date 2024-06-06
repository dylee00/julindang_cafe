package com.POG.julindang.common.exception.bookmark;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.CONFLICT)
public class BookmarkAlreadyExist extends RuntimeException{
    String message;

    public BookmarkAlreadyExist(String message) {
        super(message);
        this.message = message;
    }
}
