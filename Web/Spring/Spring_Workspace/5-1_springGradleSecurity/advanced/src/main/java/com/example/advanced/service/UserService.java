package com.example.advanced.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.advanced.model.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

import com.example.advanced.model.dto.UserDto;

@Slf4j
@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private UserRepository userRepository;

    public void joinUserDto(UserDto dto) {
        log.info("[UserService][joinUserDto] Start");

        // 권한 적용
        // id가 root면 관리자 권한, 아니면 일반
        dto.setRole("USER");

        if (dto.getUsername().equals("root")) {
            dto.setRole("ADMIN");
        }

        // 비밀번호 암호화 적용
        String rawPw = dto.getPassword();
        String encodedPw = bCryptPasswordEncoder.encode(rawPw);
        dto.setPassword(encodedPw);

        log.info("저장 직전 DTO : " + dto.toString());
        userRepository.save(dto);
    }
}
