package com.example.basic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.basic.model.dao.IProductDao;
import com.example.basic.model.dto.ProductDto;
import com.example.basic.model.entity.ProductEntity;
import com.example.basic.service.IProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements IProductService{
    
    @Autowired
    private IProductDao productDao;

    @Override
    public ProductDto findByProductName(String productName) {
        log.info("[ProductServiceImpl][findByProductName] Start");
        ProductEntity entity = productDao.findByProductName(productName);
        ProductDto dto = new ProductDto();
        dto.setProductName(entity.getProductName());
        dto.setProductPrice(entity.getProductPrice());
        dto.setCompanyName(entity.getCompanyName());
        
        return dto;
    }

    @Override
    public void saveProduct(ProductDto dto) {
        log.info("[ProductServiceImpl][saveProduct] Start!");

        ProductEntity entity = new ProductEntity();
        entity.setProductName(dto.getProductName());
        entity.setProductPrice(dto.getProductPrice());
        entity.setCompanyName(dto.getCompanyName());

        productDao.saveProduct(entity);
    }
    
}
