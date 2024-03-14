package com.POG.julindang.common.exception.dessert;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
public class DessertNameDoesNotExist extends RuntimeException{
    public DessertNameDoesNotExist() {
        super();
    }
}
