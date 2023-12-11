package com.example.advanced.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.advanced.model.dto.UserDto;
import com.example.advanced.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String index() {
        log.info("[UserController][index] Get Start");
        return "index";
    }
    
    @GetMapping("/logout")
    public String logout() {
        log.info("[UserController][logout] Get Start");
        return "redirect:/login";
    }

    @GetMapping("/join")
    public String joinPage() {
        log.info("[UserController][joinPage] Get Start");
        return "join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute UserDto dto) {
        log.info("[UserController][join] Post Start");
        log.info("joinUserDto 직전 : " + dto.toString());

        userService.joinUserDto(dto);

        return "redirect:/login";
    }

    @GetMapping("/loginPage")
    public String loginPage() {
        log.info("[UserController][loginPage] Get Start");
        return "login";
    }

    // @PostMapping("/login")
    // public String login() {
    //     log.info("[UserController][login] Post Start");
    //     return "redirect:/user";
    // }

    @GetMapping("/user")
    public String user() {
        log.info("[UserController][user] Get Start");
        return "user";
    }

    @GetMapping("/admin")
    public String admin() {
        log.info("[UserController][admin] Get Start");
        return "admin";
    }
}
