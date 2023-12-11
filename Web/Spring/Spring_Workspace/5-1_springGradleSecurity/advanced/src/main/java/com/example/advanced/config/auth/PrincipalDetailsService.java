package com.example.advanced.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.advanced.model.dto.UserDto;
import com.example.advanced.model.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
// 로그인 요청시 실행됨 -> user가 있는지 없는지만 확인
// Controller로 가기 전에 로그인 path가 넘어오면 catch해서 검증해서 Controller로 전달해준다.
public class PrincipalDetailsService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        log.info("[PrincipalDetailsService][loadUserByUsername] Start");
        log.info("userId : " + userId);
        
        UserDto userDto = userRepository.getUserDtoByUserId(userId);
        // DB에서 조회해서 user가 있는지 없는지 확인
        if (userDto != null) {
            // 이미 가입이 된 사용자
            log.info(userDto.toString());
            // spring security가 인지할 수 있는 UserDetails 객체로 변환래서 넘겨준다.
            return new PrincipalDetails(userDto);
        }

        // 가입이 되지 않은 사용자
        return null;
    }
}
