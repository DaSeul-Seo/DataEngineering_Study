package com.example.basic.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.example.basic.model.BasicDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BasicController {
    //value : "/hello", method : GET
    @GetMapping("/hello")
    public String getHello() {
        String msg = "Hello World";
        System.out.println("msg : " + msg);
        return msg;
    }

    // GetMapping과 동일하지만 method가 고정이다.
    // RequestMapping은 method 수정이 가능
    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public String getHello1() {
        String msg = "Hello World";
        log.info("msg : " + msg);
        return msg;
    }

    @GetMapping("/variable/{param}")
    public String getHello2(@PathVariable String param) {
        log.info("url로부터 param을 받았어 : " + param);
        return param;
    }

    @GetMapping("/variable/{param1}/{param2}")
    public String getHello3(@PathVariable String param1, @PathVariable String param2) {
        log.info("url로부터 param을 받았어 : " + param1);
        return param2;
    }

    @GetMapping("/variable4/{param}")
    public String getHello4(@PathVariable("param") String name) {
        log.info("url로부터 param을 받았어 : " + name);
        return name;
    }

    @GetMapping("/logging")
    public String getLogging() {
        String logMsg = "logging 들어왔어";
        log.info(logMsg);
        return "logging 했어";
    }

    // http://localhost:8080/query?key=value
    // http://localhost:8080/query?key=HelloWorld
    @GetMapping("query1")
    public String getQuery1(@RequestParam String key) {
        log.info("어서와 query는 처음이지?");
        return key;
    }

    // http://localhost:8080/query2?key1=value1&key2=value2&key3=value3
    @GetMapping("query2")
    public String getQuery2(
        @RequestParam String key1,
        @RequestParam String key2,
        @RequestParam String key3
        ) {
        log.info("어서와 다중쿼리는 처음이지?");
        return key1 + key2 + key3;
    }

    @GetMapping("query3")
    public String getQuery3(@RequestParam Map<String, Object> params) {
        log.info("어서와 다중쿼리(map)는 처음이지?");
        String msg = "";

        for (String key : params.keySet()){
            msg = msg + " " + (params.get(key)).toString();
        }
        return msg;
    }

    // http://localhost:8080/dto1?name=name1&email=email1
    @GetMapping("/dto1")
    public String getDto1(@ModelAttribute BasicDto dto) {

        return dto.toString();
    }
}
