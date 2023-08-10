package com.elyashevich.shop.service.impl;

import com.elyashevich.shop.converter.ProductDtoConverter;
import com.elyashevich.shop.dto.ProductDto;
import com.elyashevich.shop.exception.ProductNotFoundException;
import com.elyashevich.shop.exception.UserNotFoundException;
import com.elyashevich.shop.model.Product;
import com.elyashevich.shop.model.Person;
import com.elyashevich.shop.repository.ProductRepository;
import com.elyashevich.shop.repository.UserRepository;
import com.elyashevich.shop.service.ProductService;
import jakarta.annotation.security.RolesAllowed;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final UserRepository userRepository;

    private final ProductRepository productRepository;
    private final ProductDtoConverter productDtoConverter;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(@NonNull Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(
                String.format("Product with id = '%s' wasn't found!", id)
        ));
    }

    @Override
    public Product create(final @NonNull ProductDto productDto) {
        final Person user = userRepository.findByUsername(productDto.user_id()).orElseThrow(() -> new UserNotFoundException(
                String.format("USer with username = '%s' wasn't found!", productDto.user_id())
        ));
        final Product product = productDtoConverter.converter(productDto);
        product.setUser(user);
        log.info("Created: " + product);
        return productRepository.save(product);
    }

    @Override
    @RolesAllowed("ADMIN")
    public Product update(@NonNull Long id, @NonNull Product productDto) {
        final Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(
                String.format("Product with id = '%s' wasn't found!", id)
        ));
        product.setComments(productDto.getComments());
        product.setCategory(productDto.getCategory());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        log.info("Updated: " + product);
        return productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }
}
