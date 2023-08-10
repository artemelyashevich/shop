package com.elyashevich.shop.service;

import com.elyashevich.shop.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category create(Category category);
    Category update(Long id, String name);
    void delete(Long id);
}
