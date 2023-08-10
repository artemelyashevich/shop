package com.elyashevich.shop.service;

import com.elyashevich.shop.dto.CommentDto;
import com.elyashevich.shop.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findAll();
    Comment create(CommentDto commentDto);
    List<Comment> getByProduct(Long id);
    List<Comment> getByUser(Long id);
    Comment update(Long id, CommentDto commentDto);
    void delete(Long id);
}
