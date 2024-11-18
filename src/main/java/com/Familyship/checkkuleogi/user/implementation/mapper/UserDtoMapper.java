package com.Familyship.checkkuleogi.user.implementation.mapper;

import com.Familyship.checkkuleogi.user.jpa.SiteUser;
import com.Familyship.checkkuleogi.user.jpa.enums.Role;
import com.Familyship.checkkuleogi.user.dto.UserDto;
import com.Familyship.checkkuleogi.user.dto.request.CreateUserRequestDTO;
import com.Familyship.checkkuleogi.user.dto.response.CreateUserResponseDTO;
import com.Familyship.checkkuleogi.user.dto.response.LoginUserResponseDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserDtoMapper {


    public SiteUser toSiteUser(CreateUserRequestDTO createUserRequest, PasswordEncoder passwordEncoder, LocalDate date) { // date 인자 추가
        return SiteUser.builder()
                .id(createUserRequest.getId())
                .password(passwordEncoder.encode(createUserRequest.getPassword()))
                .email(createUserRequest.getEmail())
                .role(Role.USER) // 기본 역할
                .name(createUserRequest.getName())
                .birth(date)
                .gender(createUserRequest.getGender())
                .build();
    }

    public CreateUserResponseDTO toCreateUserResponse(String message) {
        return CreateUserResponseDTO.builder()
                .message(message)
                .build();
    }

    public LoginUserResponseDTO toLoginUserResponse(String token) {
        return LoginUserResponseDTO.builder()
                .token(token)
                .build();
    }

    public UserDto toUserDto(SiteUser user) {
        return new UserDto(
                user.getIdx(),
                user.getName(),
                user.getEmail(),
                user.getRole());
    }
}
