package com.elyashevich.shop.service;

import com.elyashevich.shop.dto.UserDto;
import com.elyashevich.shop.model.Person;

import java.util.List;

public interface UserService {
    Person findById(Long id);
    Person save(UserDto user);
    void delete(Long id);
    List<Person> findAll();
    void giveAdmin(Long id);
    void removeAdmin(Long id);
}
