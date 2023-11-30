package com.example.basic.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.basic.model.entity.UserEntity;
import com.example.basic.service.UserService;

@RestController
@RequestMapping("/ver1/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<UserEntity> selectProductAll() {
        return userService.selectUserAll();
    }

    @GetMapping("/filter")
    public List<UserEntity> selectUserFilter(@ModelAttribute UserEntity dto){
        return userService.selectUserFilter(dto);
    }
}
