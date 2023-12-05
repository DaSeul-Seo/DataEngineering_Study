package com.example.basic.model.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.basic.model.dao.IProductDao;
import com.example.basic.model.entity.ProductEntity;
import com.example.basic.model.repository.IProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductDaoImpl implements IProductDao{
    
    @Autowired
    private IProductRepository productRepository;

    @Override
    public ProductEntity findByProductName(String productName) {
        log.info("[ProductDaoImpl][findByProductName] Start!");
        return productRepository.findByProductName(productName);
    }

    @Override
    public void saveProduct(ProductEntity productEntity) {
        log.info("[ProductDaoImpl][saveProduct] Start!");
        productRepository.save(productEntity);
    }

    // @Override
    // public void updateProduct(ProductEntity productEntity) {
    //     log.info("[ProductDaoImpl][updateProduct] Start!");
    //     productRepository.updateProduct(productEntity);
    // }

    // @Override
    // public void deleteProduct(int productId) {
    //     log.info("[ProductDaoImpl][deleteProduct] Start!");
    //     productRepository.deleteProduct(productId);
    // }
}
