package com.elyashevich.shop.service;

import com.elyashevich.shop.dto.ProductDto;
import com.elyashevich.shop.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    Product create(ProductDto productDto);
    Product update(Long id, Product productDto);
    void remove(Long id);
}
