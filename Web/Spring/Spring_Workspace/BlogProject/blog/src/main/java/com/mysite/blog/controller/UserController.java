package com.mysite.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mysite.blog.model.dto.UserEntity;
import com.mysite.blog.service.UserService;


import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<UserEntity> selectUserAll() {
        List<UserEntity> userList = userService.selectUserAll();
        return userList;
    }

    @GetMapping("/select")
    public List<UserEntity> selectUser(@ModelAttribute UserEntity userEntity) {
        List<UserEntity> userList = userService.selectUser(userEntity);
        return userList;
    }

    @PostMapping("/insert")
    public String insertUser(@RequestBody UserEntity userEntity) {
        userService.insertUser(userEntity);
        return "Insert Success";
    }

    @PostMapping("/update")
    public String updateUser(@RequestBody UserEntity userEntity) {
        userService.updateUser(userEntity);
        return "update Success";
    }
    
    @PostMapping("/delete")
    public String deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
        return "delete Success";
    }
}
