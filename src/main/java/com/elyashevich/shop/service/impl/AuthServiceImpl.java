package com.elyashevich.shop.service.impl;

import com.elyashevich.shop.dto.UserDto;
import com.elyashevich.shop.dto.UserDtoLogin;
import com.elyashevich.shop.exception.ResourceAlreadyExistException;
import com.elyashevich.shop.repository.UserRepository;
import com.elyashevich.shop.service.AuthService;
import com.elyashevich.shop.utils.JwtTokenUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtTokenUtils jwtTokenUtils;
    private final UserServiceImpl userService;
    private final UserRepository userRepository;

    public String createAuthToken(@NonNull UserDtoLogin userDto) {
        final UserDetails userDetails = userService.loadUserByUsername(userDto.username());
        log.info("UserDetails: " + userDetails);
        return jwtTokenUtils.generateToken(userDetails);
    }

    public String createNewUser(@NonNull UserDto userDto) {
        if (userRepository.findByUsername(userDto.username()).isPresent()) {
            throw new ResourceAlreadyExistException(
                    String.format("User with username = '%s' was found!", userDto.username())
            );
        }
        userService.save(userDto);
        final UserDetails userDetails = userService.loadUserByUsername(userDto.username());
        log.info("UserDetails: " + userDetails);
        return jwtTokenUtils.generateToken(userDetails);
    }
}
