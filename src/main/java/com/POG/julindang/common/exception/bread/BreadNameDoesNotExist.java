package com.POG.julindang.common.exception.bread;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
public class BreadNameDoesNotExist extends RuntimeException{
    public BreadNameDoesNotExist() {
        super();
    }
}
