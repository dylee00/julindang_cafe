package com.POG.julindang.common.exception.user;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFound extends RuntimeException{
    String message;

    public AccountNotFound(String email) {
        super(email);
        this.message = email;
    }
}
