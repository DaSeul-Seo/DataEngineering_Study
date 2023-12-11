package com.example.todo.config;

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
public class CommonRestAdvice {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Map<String, Object>> defaultExceptionHandler(Exception e) {
        log.info("[CommonRestAdvice][defaultExceptionHandler] Starg");
        log.info("Exception message : " + e.getMessage());

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Map<String, Object> errorMsg = new HashMap<>();
        errorMsg.put("msg", "죄송합니다. 다시 시도해 주세요.");
        errorMsg.put("Exception message", e.getMessage());

        return new ResponseEntity<>(errorMsg, headers, status);
    }
}
