package com.elyashevich.shop.service.impl;

import com.elyashevich.shop.converter.CommentDtoConverter;
import com.elyashevich.shop.dto.CommentDto;
import com.elyashevich.shop.exception.CommentNotFoundException;
import com.elyashevich.shop.exception.ProductNotFoundException;
import com.elyashevich.shop.exception.UserNotFoundException;
import com.elyashevich.shop.model.Comment;
import com.elyashevich.shop.model.Product;
import com.elyashevich.shop.model.Person;
import com.elyashevich.shop.repository.CommentRepository;
import com.elyashevich.shop.repository.ProductRepository;
import com.elyashevich.shop.repository.UserRepository;
import com.elyashevich.shop.service.CommentService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CommentRepository commentRepository;
    private final CommentDtoConverter commentDtoConverter;

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment create(@NonNull CommentDto commentDto) {
        final Product product = productRepository.findById(commentDto.product()).orElseThrow(() -> new ProductNotFoundException(
                String.format("Product with id = '%s' wasn't found!", commentDto.product())
        ));
        final Person user = userRepository.findById(commentDto.user()).orElseThrow(() -> new UserNotFoundException(
                String.format("User with id = '%s' wasn't found!", commentDto.user())
        ));
        final Comment comment = commentDtoConverter.convert(commentDto);
        comment.setUser(user);
        comment.setProduct(product);
        log.info("Created: " + comment);
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getByProduct(@NonNull Long id) {
        final Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(
                String.format("Product with id = '%s' wasn't found!", id)
        ));
        return commentRepository.findAllByProduct(product);
    }

    @Override
    public List<Comment> getByUser(@NonNull Long id) {
        final Person user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(
                String.format("User with id = '%s' wasn't found!", id)
        ));
        return commentRepository.findAllByUser(user);
    }

    @Override
    public Comment update(@NonNull Long id, @NonNull CommentDto commentDto) {
        final Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException(
                String.format("Comment with id = '%s' wasn't found!", id)
        ));
        comment.setTitle(commentDto.title());
        comment.setContent(commentDto.content());
        log.info("Updated: " + comment);
        return commentRepository.save(comment);
    }

    @Override
    public void delete(@NonNull Long id) {
        commentRepository.deleteById(id);
    }
}
