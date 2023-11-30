package com.example.basic.model.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    private int userId;
    private String userName;
    private int userAge;
    private String userEmail;
}