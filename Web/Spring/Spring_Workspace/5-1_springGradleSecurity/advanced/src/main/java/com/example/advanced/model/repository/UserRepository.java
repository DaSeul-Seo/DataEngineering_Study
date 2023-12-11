package com.example.advanced.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.advanced.model.dto.UserDto;

public interface UserRepository extends JpaRepository<UserDto, String> {
    
    @Query(value = "SELECT u.* FROM custom u WHERE u.username = :username", nativeQuery = true)
    UserDto getUserDtoByUserId(@Param(value = "username") String username);
}
