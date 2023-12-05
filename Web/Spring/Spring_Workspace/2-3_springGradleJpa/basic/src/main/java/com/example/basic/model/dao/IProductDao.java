package com.example.basic.model.dao;

import com.example.basic.model.entity.ProductEntity;

public interface IProductDao {
    
    // select
    public ProductEntity findByProductName(String productName);

    // insert : JPA에서 제공하는 함수
    // 함수명 : save + 테이블명
    public void saveProduct(ProductEntity productEntity);

    // // update
    // // 함수명 : update + 테이블명
    // public void updateProduct(ProductEntity productEntity);

    // // delete
    // // 함수명 : delete + 테이블명
    // public void deleteProduct(int productId);
}