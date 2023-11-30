package com.example.basic.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BasicDto {
    private String userName;
    private int userAge;
    private String userEmail;
}
