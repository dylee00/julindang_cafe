package com.POG.julindang.common.exception.beverage;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ParameterInvalidException extends RuntimeException {
    private final String message;

    public ParameterInvalidException(String s) {
        super(s);
        message = s;
    }
}
