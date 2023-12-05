package com.example.advanced.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.advanced.model.dto.UserDto;
import java.util.List;


public interface IUserRepository extends JpaRepository<UserDto, Long> {
    
    // JPA query문 작성
    @Query(value = "SELECT user_name FROM customer WHERE id= :userId", nativeQuery = true)
    public String getUserNameById(@Param("userId") long userId);

    // JPA query Method 사용
    public List<UserDto> findByUserNameStartingWith(String firstName);

}