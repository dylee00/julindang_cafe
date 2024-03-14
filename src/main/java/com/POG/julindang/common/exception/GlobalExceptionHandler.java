package com.POG.julindang.common.exception;

import com.POG.julindang.common.exception.bookmark.BookMarkDoesNotExist;
import com.POG.julindang.common.exception.dessert.DessertNameDoesNotExist;
import com.POG.julindang.common.exception.cafe.BeverageNameDoesNotExist;
import com.POG.julindang.common.exception.cafe.CafeNameDoesNotExist;
import com.POG.julindang.common.exception.user.UserEmailDoesNotExist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handleException(HttpMessageNotReadableException ex){
        ApiErrorResponse response = new ApiErrorResponse("JEC-001", "Object Doesn't Exist");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CafeNameDoesNotExist.class)
    public ResponseEntity<ApiErrorResponse> handleException(CafeNameDoesNotExist ex){
        ApiErrorResponse response = new ApiErrorResponse("JEC-002", "Cafe Name Doesn't Exist: " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BeverageNameDoesNotExist.class)
    public ResponseEntity<ApiErrorResponse> handleException(BeverageNameDoesNotExist ex){
        ApiErrorResponse response = new ApiErrorResponse("JEC-003", "Beverage Name Doesn't Exist: " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserEmailDoesNotExist.class)
    public ResponseEntity<ApiErrorResponse> handleException(UserEmailDoesNotExist ex){
        ApiErrorResponse response = new ApiErrorResponse("JEC-004", "User Email Doesn't Exist." + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DessertNameDoesNotExist.class)
    public ResponseEntity<ApiErrorResponse> handleException(DessertNameDoesNotExist ex){
        ApiErrorResponse response = new ApiErrorResponse("JEC-005", "Bread Name Doesn't Exist." + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(BookMarkDoesNotExist.class)
    public ResponseEntity<ApiErrorResponse> handleException(BookMarkDoesNotExist ex){
        ApiErrorResponse response = new ApiErrorResponse("JEC-006", "Bread Name Doesn't Exist. [Cafe Name, Beverage Name, User Email] : [" + ex.getMessage() + "]");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }
}
