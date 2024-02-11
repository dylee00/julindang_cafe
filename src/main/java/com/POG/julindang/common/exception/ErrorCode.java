package com.POG.julindang.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public enum ErrorCode {
    /**
     * 1xxx -> Common 에러
     */
    BAD_REQUEST(1000, "Bad Request", HttpStatus.BAD_REQUEST),
    NOT_FOUND(1001, "Contents Not Found", HttpStatus.NOT_FOUND),
    METHOD_NOT_ALLOWED(1002, "Method Not Allowed", HttpStatus.METHOD_NOT_ALLOWED),
    INTERNAL_SERVER_ERROR(1003, "Internal Server Error Occurred", HttpStatus.INTERNAL_SERVER_ERROR),
    METHOD_ARGUMENT_NOT_VALID(1004, "Method Argument Is Not Valid", HttpStatus.BAD_REQUEST),
    OBJECT_NOT_FOUND(1005, "Object Is Null", HttpStatus.NOT_FOUND),

    /**
     * 2xxx -> 카페 관련 에러
     */
    CAFE_NAME_DOES_NOT_EXIST(2000, "Cafe Name Doesn't Exist", HttpStatus.NOT_FOUND),
    BEVERAGE_NAME_DOES_NOT_EXIST(2001, "Beverage Name Doesn't Exist", HttpStatus.NOT_FOUND),
    CAFE_DOES_NOT_EXIST(2002, "Cafe Object Doesn't Exist", HttpStatus.NOT_FOUND),
    DATE_NOT_FOUND(2003, "Date Doesn't Exist", HttpStatus.NOT_FOUND),

    /**
     * 3xxx -> 계정 관련 에러
     */

    EMAIL_NOT_FOUND(3000, "Email Doesn't Exist", HttpStatus.NOT_FOUND);


    private final int code;
    private final String message;
    private final HttpStatus httpStatus;
    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;

    }
}
