package com.elyashevich.shop.repository;

import com.elyashevich.shop.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByUsername(String username);
}
