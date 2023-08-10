package com.elyashevich.shop.repository;

import com.elyashevich.shop.model.Comment;
import com.elyashevich.shop.model.Product;
import com.elyashevich.shop.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByProduct(Product product);
    List<Comment> findAllByUser(Person user);
}
