package com.POG.julindang.common.exception;

import com.POG.julindang.common.exception.beverage.ParameterInvalidException;
import com.POG.julindang.common.exception.bookmark.BookMarkDoesNotExist;
import com.POG.julindang.common.exception.bookmark.BookmarkAlreadyExist;
import com.POG.julindang.common.exception.bookmark.BookmarkTypeDoesNotExist;
import com.POG.julindang.common.exception.bookmark.InvalidBookmarkType;
import com.POG.julindang.common.exception.cafe.CafeDoesNotExist;
import com.POG.julindang.common.exception.dessert.DessertDoesNotExist;
import com.POG.julindang.common.exception.dessert.DessertNameDoesNotExist;
import com.POG.julindang.common.exception.cafe.BeverageNameDoesNotExist;
import com.POG.julindang.common.exception.cafe.CafeNameDoesNotExist;
import com.POG.julindang.common.exception.user.AccountNotFound;
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
        ApiErrorResponse response = new ApiErrorResponse("JEC-006", "Bread Name Doesn't Exist. [" + ex.getMessage() + "]");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(BookmarkTypeDoesNotExist.class)
    public ResponseEntity<ApiErrorResponse> handleException(BookmarkTypeDoesNotExist ex){
        ApiErrorResponse response = new ApiErrorResponse("JEC-007", "Bookmark Type Doesn't Exist.");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(InvalidBookmarkType.class)
    public ResponseEntity<ApiErrorResponse> handleException(InvalidBookmarkType ex){
        ApiErrorResponse response = new ApiErrorResponse("JEC-008", "Invalid Bookmark Type. Type : "+ ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(BookmarkAlreadyExist.class)
    public ResponseEntity<ApiErrorResponse> handleException(BookmarkAlreadyExist ex){
        ApiErrorResponse response = new ApiErrorResponse("JEC-009", "Bookmark Already Exist Bookmark Info : [" + ex.getMessage() +"]");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(AccountNotFound.class)
    public ResponseEntity<ApiErrorResponse> handleException(AccountNotFound ex){
        ApiErrorResponse response = new ApiErrorResponse("JEM-003", "This is not member. member email : " +ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(CafeDoesNotExist.class)
    public ResponseEntity<ApiErrorResponse> handleException(CafeDoesNotExist ex){
        ApiErrorResponse response = new ApiErrorResponse("JEC-010", "Cafe Does Not Exist. Info : [" +ex.getMessage() + "]");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(DessertDoesNotExist.class)
    public ResponseEntity<ApiErrorResponse> handleException(DessertDoesNotExist ex){
        ApiErrorResponse response = new ApiErrorResponse("JEC-011", "Dessert Does Not Exist. Info : [" +ex.getMessage() + "]");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(ParameterInvalidException.class)
    public ResponseEntity<ApiErrorResponse> handleException(ParameterInvalidException ex){
        return new ResponseEntity<>(
                new ApiErrorResponse(
                        "JEC-012",
                        ex.getMessage()
                ),
                HttpStatus.BAD_REQUEST
        );
    }
}
