package com.example.basic.model.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductEntity {
    private int productId;
    private String productName;
    private int productPrice;
}
