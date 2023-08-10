package com.elyashevich.shop.service;

import com.elyashevich.shop.dto.UserDto;
import com.elyashevich.shop.dto.UserDtoLogin;

public interface AuthService {
    String createAuthToken(UserDtoLogin userDto);
    String createNewUser(UserDto userDto);
}
