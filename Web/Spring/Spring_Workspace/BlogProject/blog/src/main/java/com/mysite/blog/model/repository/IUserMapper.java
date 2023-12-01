package com.mysite.blog.model.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.*;
import com.mysite.blog.model.dto.UserEntity;

@Mapper
public interface IUserMapper {
    public List<UserEntity> selectUserAll();
    public List<UserEntity> selectUser(UserEntity userEntity);
    public void insertUser(UserEntity userEntity);
    public void updateUser(UserEntity userEntity);
    public void deleteUser(@Param("userId") int userId);
}
