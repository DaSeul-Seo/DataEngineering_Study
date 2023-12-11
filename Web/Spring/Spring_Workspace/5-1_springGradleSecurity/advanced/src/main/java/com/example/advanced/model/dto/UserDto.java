package com.example.advanced.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "UserDto")
@Table(name = "custom")
public class UserDto {

    @Id
    private String username;

    private String password;

    @Column(unique = true)
    private String email;
    
    // 일반 사용자, 관리자를 구분하기 위함
    private String role;
}
