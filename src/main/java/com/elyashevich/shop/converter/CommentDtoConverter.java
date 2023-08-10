package com.elyashevich.shop.converter;

import com.elyashevich.shop.dto.CommentDto;
import com.elyashevich.shop.model.Comment;
import org.springframework.stereotype.Service;

@Service
public class CommentDtoConverter {

    public Comment convert(CommentDto commentDto) {
        return Comment.builder()
                .title(commentDto.title())
                .content(commentDto.content())
                .build();
    }
}
