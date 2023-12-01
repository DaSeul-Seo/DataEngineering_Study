package com.mysite.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.blog.model.dto.UserEntity;
import com.mysite.blog.model.repository.IUserMapper;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private IUserMapper userMapper;

    public List<UserEntity> selectUserAll() {
        List<UserEntity> userList = userMapper.selectUserAll();
        return userList;
    }

    public List<UserEntity> selectUser(UserEntity userEntity) {
        List<UserEntity> userList = userMapper.selectUser(userEntity);
        return userList;
    }

    public void insertUser(UserEntity userEntity) {
        if (userEntity.getUserName() != null
            | userEntity.getUserAgeYear() != 0
            | userEntity.getUserAgeMonth() != 0
            | userEntity.getUserAgeDay() != 0
            | userEntity.getUserEmail() != null) {
            userMapper.insertUser(userEntity);
        }
    }

    public void updateUser(UserEntity userEntity) {
        if (userEntity.getUserId() != 0) {
            userMapper.updateUser(userEntity);
        }
    }

    public void deleteUser(int userId) {
        if (userId != 0){
            userMapper.deleteUser(userId);
        }
    }
}
