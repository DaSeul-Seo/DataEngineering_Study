package com.example.basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.basic.model.dto.UserDto;
import com.example.basic.model.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

     // 가입 (insert)
    public void joinUser(UserDto dto) {

        dto.setUserRole("custom");

        // 만약 id가 root 이면 admin 권한 부여
        if (dto.getUserId().equals("root")){
            dto.setUserRole("admin");
        }
        // else if (dto.getUserId() == null || dto.getUserEmail() == null || dto.getUserPw()) {

        // }
        
        userRepository.save(dto);
    }

    // 로그인 (select)
    public UserDto loginUser(UserDto dto) {
        // 1. userId에 해당하는 user가 있는지?
        // 1-1. 있다면 pw가 같은지 확인 -> 다르면 오류
        // 1-2. 없다면 신규가입 유도

        UserDto savedUser = userRepository.getUserDtoByUserId(dto.getUserId());

        // 신규가입 유도
        if (savedUser == null) {
            return null;
        }
        // 회원이면 비밀번호 동일한지 검증
        else if (!savedUser.getUserPw().equals(dto.getUserPw())) {
            // dto는 email과 role이 없다.
            return dto;
        }
        return savedUser;
    }

}
