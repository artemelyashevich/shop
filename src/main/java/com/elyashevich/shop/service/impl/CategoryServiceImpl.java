package com.elyashevich.shop.service.impl;

import com.elyashevich.shop.exception.CategoryNotFoundException;
import com.elyashevich.shop.model.Category;
import com.elyashevich.shop.repository.CategoryRepository;
import com.elyashevich.shop.service.CategoryService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category create(@NonNull Category category) {
        log.info("Created: " + category);
        return categoryRepository.save(category);
    }

    @Override
    public Category update(@NonNull Long id, @NonNull String name) {
        final Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(
                String.format("Category with id = '%s' wasn't found!", id)
        ));
        category.setName(name);
        log.info("Updated: " + category);
        return categoryRepository.save(category);
    }

    @Override
    public void delete(@NonNull Long id) {
        categoryRepository.deleteById(id);
    }
}
