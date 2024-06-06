package com.POG.julindang.common.exception.dessert;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DessertDoesNotExist extends RuntimeException{
    String message;

    public DessertDoesNotExist(String message) {
        super(message);
        this.message = message;
    }
}
