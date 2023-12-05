package com.example.basic.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.basic.model.entity.ProductEntity;

public interface IProductRepository extends JpaRepository<ProductEntity, Long> {
    
    public ProductEntity findByProductName(String productName);

    
}
