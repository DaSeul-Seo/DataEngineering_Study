package com.example.basic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.basic.model.dto.UserDto;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController {
    
    @GetMapping("/hello")
    public String hello() {
        log.info("[UserController][hello] Start");
        return "hello";
    }

    @PostMapping("/join/novalid")
    public String joinNoValid(@RequestBody UserDto dto) {
        log.info("[UserController][joinNoValid] Start");
        log.info("novalid DTO : " + dto.toString());
        return "Success without Validation";
    }

    @PostMapping("/join/valid")
    public String joinValid(@Valid @RequestBody UserDto dto) {
        log.info("[UserController][joinValid] Start");
        log.info("valid DTO : " + dto.toString());
        return "Success with Validation";
    }
}
