package com.syhwang.board.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

//    @ExceptionHandler
//    public ResponseEntity<String> duplicateLoginIdExHandle(DuplicateLoginIdException e) {
//        return new ResponseEntity<>(e.getMessage(), )
//    }
}
