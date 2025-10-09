package com.meecaps.socialApp.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(UserNotFound.class)

    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFound exception, HttpServletRequest request){

         ErrorResponse errorResponse= new ErrorResponse(
                 LocalDateTime.now(),
                  HttpStatus.NOT_FOUND.value(),
                 HttpStatus.NOT_FOUND.getReasonPhrase(),
                 exception.getMessage(),
                 request.getRequestURI()

         );
        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(404));

    }


}
