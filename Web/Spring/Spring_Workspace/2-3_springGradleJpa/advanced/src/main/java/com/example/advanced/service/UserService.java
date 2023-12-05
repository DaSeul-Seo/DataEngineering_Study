package com.example.advanced.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.advanced.model.dto.UserDto;
import com.example.advanced.model.repository.IUserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
    
    @Autowired
    private IUserRepository userRepository;

    public UserDto getDtoById(long id) {
        log.info("[UserService][getDtoById] Start");

        if (id == 0) { 
            log.error("Error : [UserService][getDtoById]");
            return new UserDto(); 
        }
        // 검증 로직 통과하면 비즈니스 로직 실행
        return userRepository.getReferenceById(id);
    }

    public void insertDto(UserDto dto) {
        log.info("[UserService][insertDto] Start");
        userRepository.save(dto);
    }

    public String getUserNameById(long userId) {
        log.info("[UserService][getUserNameById] Start");
        log.info("userId: " + userId);

        return userRepository.getUserNameById(userId);
    }

    public List<UserDto> findByNameStartingWith(String firstName) {
        log.info("[UserService][findByNameStartingWith] Start");
        log.info("userName: " + firstName);

        return userRepository.findByUserNameStartingWith(firstName);
    }
}
