package com.example.basic.controller;

import java.util.*;

import org.springframework.web.bind.annotation.*;

import com.example.basic.model.UserDto;

// 요청 처리
@RestController
public class BasicController {
    @GetMapping("/hello")
    public String getHello() {
        return "Hello World";
    }

    @PostMapping("/variable1")
    public String postVariable1(@RequestBody Map<String, Object> params){
        String msg = "";

        for (String key : params.keySet()){
            msg += params.get(key).toString();
        }
        return msg;
    }

    @RequestMapping(value = "/dto1", method = RequestMethod.POST)
    public String postDto1(@ModelAttribute UserDto dto){
        return dto.toString();
    }
}
