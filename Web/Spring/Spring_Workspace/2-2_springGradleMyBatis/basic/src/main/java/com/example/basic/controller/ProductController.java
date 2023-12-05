package com.example.basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.basic.model.dto.ProductDto;
import com.example.basic.service.ProductServie;

@RestController
@RequestMapping("/ver2/product")
public class ProductController {
    @Autowired
    private ProductServie productService;

    @GetMapping("/all")
    public List<ProductDto> selectProductAll() {
        List<ProductDto> productList = productService.selectProductAll();
        return productList;
    }

    @GetMapping("/select")
    public List<ProductDto> selectProduct(@ModelAttribute ProductDto dto) {
        List<ProductDto> productList = productService.selectProduct(dto);
        return productList;
    }

    @GetMapping("/insert")
    @PostMapping("/insert")
    public String insertProduct(@RequestBody ProductDto dto) {
        productService.insertProduct(dto);

        return "저장 성공";
    }

    @PostMapping("/update")
    public String updateProduct(@RequestBody ProductDto dto) {
        productService.updateProduct(dto);
        return "업데이트 성공";
    }

    @GetMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable int productId) {
        productService.deleteProduct(productId);
        return "삭제 성공";
    }

}
