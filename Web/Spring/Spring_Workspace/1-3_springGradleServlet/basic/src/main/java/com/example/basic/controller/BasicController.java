package com.example.basic.controller;

import org.springframework.web.bind.annotation.*;

import com.example.basic.model.BasicDto;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BasicController {
    @GetMapping("/hello")
    public String getHello() {
        log.info("[BasicController][getHello] Start!!");
        return "Hello World";
    }

    @GetMapping("/test")
    public String getTest(@ModelAttribute BasicDto dto){
        log.info("[BasicController][getTest] Start!!");
        return dto.toString();
    }
}
