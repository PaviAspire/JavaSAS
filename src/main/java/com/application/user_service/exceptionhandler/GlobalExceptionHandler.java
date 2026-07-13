package com.application.user_service.exceptionhandler;

import com.application.user_service.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException{
/*    private String mssg;
    public GlobalExceptionHandler(String mssg){
        super(mssg);
        this.mssg=mssg;
    }*/
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundHandler(ResourceNotFoundException ex, HttpServletRequest servletRequest){
          return ResponseEntity.status(HttpStatus.NOT_FOUND)
                  .body(new ErrorResponse(HttpStatus.NOT_FOUND.toString(), LocalDateTime.now(),servletRequest.getRequestURI(),ex.getMessage()));

    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> resourceAlreadyExistsHandler(ResourceAlreadyExistsException ex, HttpServletRequest servletRequest){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(HttpStatus.CONFLICT.toString(), LocalDateTime.now(),servletRequest.getRequestURI(),ex.getMessage()));

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> genericHandler(Exception ex, HttpServletRequest servletRequest){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(HttpStatus.CONFLICT.toString(), LocalDateTime.now(),servletRequest.getRequestURI(),ex.getMessage()));

    }
}
