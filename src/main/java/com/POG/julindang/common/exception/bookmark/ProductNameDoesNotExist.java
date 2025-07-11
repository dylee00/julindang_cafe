package com.POG.julindang.common.exception.bookmark;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNameDoesNotExist extends RuntimeException{
    public ProductNameDoesNotExist() {
        super();
    }
}
