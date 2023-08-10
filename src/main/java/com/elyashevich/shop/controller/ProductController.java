package com.elyashevich.shop.controller;

import com.elyashevich.shop.dto.ProductDto;
import com.elyashevich.shop.model.Product;
import com.elyashevich.shop.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Validated
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(final @PathVariable Long id) {
        return productService.findById(id);
    }

    @PutMapping("/{id}")
    public Product update(
            final @PathVariable Long id,
            final @RequestBody Product product
    ) {
        return productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    public void delete(final @PathVariable Long id) {
        productService.remove(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(final @Valid @RequestBody ProductDto productDto) {
        return productService.create(productDto);
    }
}
