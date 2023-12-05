package com.example.basic.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ver2/basic")
public class BasicController {
    
    @GetMapping("/hello")
    public String getHello() {
        return "Hello";
    }
}
