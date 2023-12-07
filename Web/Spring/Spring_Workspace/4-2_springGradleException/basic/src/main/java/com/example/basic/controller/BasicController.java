package com.example.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.basic.service.BasicService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BasicController {

    @Autowired
    private BasicService basicService;

    @GetMapping("/hello")
    public String hello() {
        String msg = "Hello World";
        log.info(msg);
        try {
            int i = 1/0;
            log.info("i" + i);
        }
        catch (Exception e) {
            msg = e.getMessage();
        }
        finally {

        }
        return "";
    }

    @GetMapping("/exT1")
    public void exceptionTest1() throws Exception {
        throw new Exception("Exception 발생");
    }

    @GetMapping("/exT2")
    public String exceptionTest2() {
        try{
            throw new Exception("Exception 발생");
        }
        catch(Exception e) {
            
        }
        
        return "좋은아침";
    }

    // 정상 테스트
    @GetMapping("/ok")
    public String ok() throws Exception{
        return basicService.test();
    }

    // Controller 오류 테이스
    @GetMapping("/exception/controller")
    public String controllerException() throws Exception {
        throw new Exception("[BasicController] 오류입니다.");
    }

    // Service 오류 테스트
    @GetMapping("/exception/service")
    public String serviceException() throws Exception {
        basicService.testException();
        return "";
    }

    // DAO 오류 테스트
    @GetMapping("/exception/dao")
    public String daoException() throws Exception {
        basicService.daoException();
        return "";
    }
}
