package com.elyashevich.shop.controller;

import com.elyashevich.shop.model.Category;
import com.elyashevich.shop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @PostMapping
    public Category create(final @RequestBody Category category) {
        return categoryService.create(category);
    }

    @PutMapping("/{id}")
    public Category update(
            final @PathVariable Long id,
            final @RequestBody String name
    ) {
        return categoryService.update(id, name);
    }

    @DeleteMapping("/{id}")
    public void delete(final @PathVariable Long id) {
        categoryService.delete(id);
    }
}
