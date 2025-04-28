package com.wcs.demo.ExceptionHandler;

import com.wcs.demo.Exception.CollegeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.wcs.demo.ErrorResponse.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobleExceptionHandler {

    @ExceptionHandler(CollegeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCollegeNotFoundException(CollegeNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                ex.getMessage(),
                "With this college Id there is not any college register !   "
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
