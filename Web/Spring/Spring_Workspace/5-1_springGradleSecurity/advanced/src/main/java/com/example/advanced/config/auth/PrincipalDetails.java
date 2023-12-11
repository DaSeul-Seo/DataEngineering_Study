package com.example.advanced.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.advanced.model.dto.UserDto;

import lombok.AllArgsConstructor;

import java.util.*;

// Spring Secutity가 이해할 수 있는 DTO를 만든다
@AllArgsConstructor
public class PrincipalDetails implements UserDetails{

    @Autowired
    private UserDto userDto;

    // 사용자의 권한 리스트 주입
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collention = new ArrayList<>();

        // 권한 생성
        collention.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {
                // userDto의 권한이 추가됨
                return userDto.getRole();
            }
        });
        return collention;
    }

    @Override
    public String getPassword() {
        return userDto.getPassword();
    }

    @Override
    public String getUsername() {
        return userDto.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // 계정 만료 유무 확인
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정 잠긴 유무 확인 
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 계정 비번 오래 사용했는지 유무 확인 
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 활성화된 계정인지 유무 확인
        return true;
    }
    
}
