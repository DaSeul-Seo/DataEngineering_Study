package com.example.basic.service;

import com.example.basic.model.dto.ProductDto;

public interface IProductService {
    
    public ProductDto findByProductName(String productName);
    public void saveProduct(ProductDto dto);
}
