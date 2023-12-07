package com.example.basic.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {

    @NotBlank
    @Size(min = 2, max = 12)
    private String userId;

    @NotBlank
    @Size(min = 2, max = 20)
    private String userPw;

    @NotBlank
    private String userName;
    
    @Min(value = 0)
    @Max(value = 120)
    private int userAge;
    
    private String userAddr;

    @Pattern(regexp = "^01(?:0|1|[6-9])-(\\d{3,4})-\\d{4}$", message = "유효한 핸드폰 번호가 아닙니다.")
    private String userPhoneNum;
}
