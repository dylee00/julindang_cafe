package com.POG.julindang.common.exception.bookmark;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidBookmarkType extends RuntimeException{
    String message;
    public InvalidBookmarkType(Integer type) {
        super(type.toString());
        this.message = type.toString();
    }
}
