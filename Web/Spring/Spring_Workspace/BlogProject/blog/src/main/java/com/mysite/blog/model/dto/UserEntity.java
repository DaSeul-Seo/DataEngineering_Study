package com.mysite.blog.model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    private int userId;
    private String userName;
    private int userAgeYear;
    private int userAgeMonth;
    private int userAgeDay; 
    private String userEmail;
}
