package com.example.basic.controller;

import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ver1/basic")
public class BasicController {

    @GetMapping("/hello")
    public String getHello() {
        String msg = "[BasicController][getHello] Start";
        log.info(msg);
        return msg;
    }
}
