package com.example.basic.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")        // jpa가 자동으로 테이블 만들어줌
@Entity(name = "ProductEntity") // product 테이블과 연결된 java 클래스명
public class ProductEntity {

    @Id // 기본키 선언!!
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true) // 제약조건
    private String productName;

    private int productPrice;

    private String companyName;
}
