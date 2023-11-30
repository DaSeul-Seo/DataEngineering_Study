package com.example.basic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.basic.model.entity.ProductEntity;
import com.example.basic.model.repository.ProductMapper;

// Business Logic
@Service
public class ProductService {

    // comfile 될 때 instance화 > Service가 만들어질때 같이 만들어짐
    @Autowired
    private ProductMapper productMapper;

    // Service가 Mapper 한테 데이터 조회 요청
    public List<ProductEntity> selectProductAll() {
        List<ProductEntity> productList = productMapper.selectProductAll();
        return productList;
    }

    public List<ProductEntity> selectProductFilter(ProductEntity productEntity) {
        List<ProductEntity> productList = productMapper.selectProductFilter(productEntity.getProductPrice(),
                                                                            productEntity.getProductName());
        return productList;
    }
}