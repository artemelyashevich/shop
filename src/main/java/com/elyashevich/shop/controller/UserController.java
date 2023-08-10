package com.elyashevich.shop.controller;

import com.elyashevich.shop.dto.UserDto;
import com.elyashevich.shop.model.Person;
import com.elyashevich.shop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<Person> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Person findById(final @PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person create(final @Valid @RequestBody UserDto user) {
        return userService.save(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(final @PathVariable Long id) {
        userService.delete(id);
    }

    @PutMapping("/{id}/giveAdmin")
    @ResponseStatus(HttpStatus.CREATED)
    public void giveAdmin(final @PathVariable Long id) {
        userService.giveAdmin(id);
    }

    @PutMapping("/{id}/removeAdmin")
    @ResponseStatus(HttpStatus.CREATED)
    public void removeAdmin(final @PathVariable Long id) {
        userService.removeAdmin(id);
    }
}
