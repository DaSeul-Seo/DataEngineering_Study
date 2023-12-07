package com.example.basic.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class BasicControllerAdvice {
    
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Map<String, String>> basicExceptionHandler(Exception e) {
        log.info("[BasicControllerAdvice][basicExceptionHandler] Start");
        log.info("exception message: " + e.getMessage());

        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        Map<String, String> msgMap = new HashMap<>();
        // msgMap.put("error type", e.getMessage());
        // msgMap.put("error code", "800");
        msgMap.put("view msg", "다시 시도해주세요.");

        return new ResponseEntity<>(msgMap, responseHeaders, httpStatus);
    }
}
