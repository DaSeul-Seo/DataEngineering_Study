package com.example.basic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/admin") // admin으로 시작하는 모든 요청
public class AdminController {
    // localhost:8080/admin/hello
    @GetMapping("/hello")
    public String getHello() {
        log.info("[AdminController][getHello] Start!!");
        return "Admin Hello";
    }

    // localhost:8080/admin/world
    @GetMapping("/world")
    public String getWorld() {
        log.info("[AdminController][getWorld] Start!!");
        return "Admin World";
    }
}
