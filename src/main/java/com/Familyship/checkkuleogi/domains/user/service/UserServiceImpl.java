package com.Familyship.checkkuleogi.domains.user.service;

import com.Familyship.checkkuleogi.domains.user.dto.UserDto;
import com.Familyship.checkkuleogi.domains.user.dto.request.CreateUserRequestDTO;
import com.Familyship.checkkuleogi.domains.user.dto.request.LoginUserRequestDTO;
import com.Familyship.checkkuleogi.domains.user.dto.response.CreateUserResponseDTO;
import com.Familyship.checkkuleogi.domains.user.dto.response.LoginUserResponseDTO;
import com.Familyship.checkkuleogi.domains.user.implementation.UserManager;
import com.Familyship.checkkuleogi.security.jwt.JwtProvider;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final UserManager userManager;

    @Override
    @Transactional
    public CreateUserResponseDTO join(CreateUserRequestDTO createUserRequest) {
        return userManager.createUser(createUserRequest, passwordEncoder);
    }

    @Override
    @Transactional
    public LoginUserResponseDTO login(LoginUserRequestDTO loginUserRequest) {
        return userManager.loginUser(loginUserRequest, passwordEncoder, jwtProvider);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto findUserById(Long id) {
        return userManager.findUserById(id);
    }
}
