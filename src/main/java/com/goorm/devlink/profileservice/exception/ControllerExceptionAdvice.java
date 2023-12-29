package com.goorm.devlink.profileservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice(basePackages = "com.goorm.devlink.profileservice.controller")
public class ControllerExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResult> getHandler(MethodArgumentNotValidException exception, HttpServletRequest request) {
        return new ResponseEntity<>(ErrorResult.getInstance(getMethodArgumentNotValidMessage(exception),request.getRequestURL().toString()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ErrorResult> getHandler(MissingRequestHeaderException exception, HttpServletRequest request) {
        return new ResponseEntity<>(ErrorResult.getInstance(exception.getMessage(), request.getRequestURL().toString()),
                HttpStatus.BAD_REQUEST);
    }

    private List<String> getMethodArgumentNotValidMessage(MethodArgumentNotValidException ex){
        ArrayList<String> errorMessages = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorMessages.add("[" + fieldError.getField() +"]는(은) " + fieldError.getDefaultMessage() +
                    " [ 입력된 값 : " + fieldError.getRejectedValue() + " ]");
        }
        return errorMessages;
    }
}
