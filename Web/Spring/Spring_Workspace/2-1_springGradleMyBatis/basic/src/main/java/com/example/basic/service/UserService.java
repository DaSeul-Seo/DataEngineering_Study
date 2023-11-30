package com.example.basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.basic.model.entity.UserEntity;
import com.example.basic.model.repository.UserMapper;

@Service
public class UserService {
    
    @Autowired
    private UserMapper userMapper;

    public List<UserEntity> selectUserAll() {
        List<UserEntity> userList = userMapper.selectUserAll();
        return userList;
    }

    public List<UserEntity> selectUserFilter(UserEntity userEntity) {
        List<UserEntity> userList = userMapper.selectUserFilter(userEntity.getUserAge());
        return userList;
    }
}
