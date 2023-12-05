package com.example.advanced.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.advanced.model.dto.UserDto;
import com.example.advanced.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/ver1/customer")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public UserDto getDtoById(@PathVariable long id) {
        log.info("[UserController][getDtoById] Start");
        log.info("id : " + id);
        
        UserDto dto =  userService.getDtoById(id);
        // log.info("dto.toString() : " + dto.toString());
        
        return dto;
    }

    @GetMapping("/username/{userId}")
    public String getUserNameById(@PathVariable long userId) {
        log.info("[UserController][getUserNameById] Start");
        log.info("userId: "+userId);

        return userService.getUserNameById(userId);
    }

    @GetMapping("/find/firstName/{firstName}")
    public List<UserDto> findByNameStartingWith(@PathVariable String firstName) {
        log.info("[UserController][findByNameStartingWith] Start");
        log.info("firstName: "+firstName);

        return userService.findByNameStartingWith(firstName);
    }

    @PostMapping("/insert")
    public String insertDto(@RequestBody UserDto dto) {
        log.info("[UserController][insertDto] Start");
        log.info("Insert dto : " + dto.toString());
        userService.insertDto(dto);

        return "Insert Success!";
    }

}
