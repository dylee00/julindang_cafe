package com.POG.julindang.common.exception.cafe;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
public class BeverageNameDoesNotExist extends RuntimeException{

    public BeverageNameDoesNotExist() {
        super();
    }
}
