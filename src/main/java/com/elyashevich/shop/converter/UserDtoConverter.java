package com.elyashevich.shop.converter;

import com.elyashevich.shop.dto.UserDto;
import com.elyashevich.shop.model.Person;
import org.springframework.stereotype.Service;

@Service
public class UserDtoConverter {

    public Person convert(UserDto user) {
        return Person.builder()
                .firstName(user.firstName())
                .lastName(user.lastName())
                .username(user.username())
                .password(user.password())
                .email(user.email())
                .build();
    }
}
