package com.example.basic.model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private String productName;

    private int productPrice;
    
    private String companyName;
}
