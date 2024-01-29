package com.blog.blogging_application.exception;

import com.blog.blogging_application.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
   @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse>resourceNotFoundException(ResourceNotFoundException ex){
        String message=ex.getMessage();
        ApiResponse apiResponce=new ApiResponse(message,false);
        return new ResponseEntity<ApiResponse>(apiResponce, HttpStatus.NOT_FOUND);
    }
}
