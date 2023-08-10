package com.elyashevich.shop.converter;

import com.elyashevich.shop.dto.ProductDto;
import com.elyashevich.shop.model.Product;

import org.springframework.stereotype.Service;

@Service
public class ProductDtoConverter {

    public Product converter(ProductDto product) {
        return Product.builder()
                .title(product.title())
                .description(product.description())
                .price(product.price())
                .build();
    }
}
