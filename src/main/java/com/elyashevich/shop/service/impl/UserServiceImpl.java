package com.elyashevich.shop.service.impl;

import com.elyashevich.shop.converter.UserDtoConverter;
import com.elyashevich.shop.dto.UserDto;
import com.elyashevich.shop.exception.UserNotFoundException;
import com.elyashevich.shop.model.Person;
import com.elyashevich.shop.model.role.Role;
import com.elyashevich.shop.model.role.RoleName;
import com.elyashevich.shop.repository.RoleRepository;
import com.elyashevich.shop.repository.UserRepository;
import com.elyashevich.shop.service.UserService;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserDtoConverter converter;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Person findById(@NonNull Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(
                String.format("User with id = '%s' wasn't found!", id)
        ));
    }

    @Override
    public Person save(@NonNull UserDto userDto) {
        final Person user = converter.convert(userDto);
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(RoleName.ROLE_USER));
        user.setPassword(passwordEncoder.encode(userDto.password()));
        user.setRoles(roles);
        log.info("User role: %s".formatted(user.getRoles()));
        return userRepository.save(user);
    }

    @Override
    public void delete(@NonNull Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<Person> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void giveAdmin(@NonNull Long id) {
        final Person user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(
                String.format("User with id = '%s' wasn't found!", id)
        ));
        Set<Role> roles = new HashSet<>(user.getRoles());
        Role role = roleRepository.findByName(RoleName.ROLE_ADMIN);
        if (!roles.contains(role)) {
            roles.add(role);
            user.setRoles(roles);
            userRepository.save(user);
        }
    }

    @Override
    public void removeAdmin(@NonNull Long id) {
        final Person user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(
                String.format("User with id = '%s' wasn't found!", id)
        ));
        Set<Role> roles = new HashSet<>(user.getRoles());
        Role role = roleRepository.findByName(RoleName.ROLE_ADMIN);
        if (roles.contains(role)) {
            roles.remove(role);
            user.setRoles(roles);
            userRepository.save(user);
        }
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        final Person user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User with username = '%s' wasn't found!", username)
        ));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName().name())).toList());
    }
}
