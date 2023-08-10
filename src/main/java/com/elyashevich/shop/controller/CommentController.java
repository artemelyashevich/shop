package com.elyashevich.shop.controller;

import com.elyashevich.shop.dto.CommentDto;
import com.elyashevich.shop.model.Comment;
import com.elyashevich.shop.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public List<Comment> findAll() {
        return commentService.findAll();
    }

    @GetMapping("/user/{id}")
    public List<Comment> findAllByUser(final @PathVariable Long id) {
        return commentService.getByUser(id);
    }

    @GetMapping("/product/{id}")
    public List<Comment> findAllByProduct(final @PathVariable Long id) {
        return commentService.getByProduct(id);
    }

    @PostMapping
    public Comment create(final @RequestBody CommentDto commentDto) {
        return commentService.create(commentDto);
    }

    @PutMapping("/{id}")
    public Comment update(
            final @PathVariable Long id,
            final @RequestBody CommentDto commentDto
    ) {
        return commentService.update(id, commentDto);
    }

    @DeleteMapping("/{id}")
    public void delete(final @PathVariable Long id) {
        commentService.delete(id);
    }
}
