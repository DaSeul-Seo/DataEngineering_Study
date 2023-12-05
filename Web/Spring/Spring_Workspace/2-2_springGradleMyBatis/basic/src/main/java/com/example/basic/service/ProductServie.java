package com.example.basic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.basic.model.dto.ProductDto;
import com.example.basic.model.repository.ProductMapper;

@Service
public class ProductServie {
    @Autowired
    private ProductMapper productMapper;
    
    public List<ProductDto> selectProductAll() {
        List<ProductDto> productList = productMapper.selectProductAll();
        return productList;
    }

    public List<ProductDto> selectProduct(ProductDto dto) {
        return productMapper.selectProduct(dto);
    }

    public void insertProduct(ProductDto dto) {
        if(dto.getProductName() != null) {
            String productName = dto.getProductName();
            productName += "~~^^";
            dto.setProductName(productName);
        }

        productMapper.insertProduct(dto);
    }

    public void updateProduct(ProductDto dto) {
        if(dto.getProductId() != 0) {
            productMapper.updateProduct(dto);
        }
    }

    public void deleteProduct(int productId) {
        if(productId != 0) {
            productMapper.deleteProduct(productId);
        }
    }
}
