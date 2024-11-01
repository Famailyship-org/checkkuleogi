package com.Familyship.checkkuleogi.domains.user.implementation;

import com.Familyship.checkkuleogi.domains.user.domain.SiteUser;
import com.Familyship.checkkuleogi.domains.user.domain.enums.Role;
import com.Familyship.checkkuleogi.domains.user.domain.repository.UserRepository;
import com.Familyship.checkkuleogi.domains.user.dto.UserDto;
import com.Familyship.checkkuleogi.domains.user.dto.request.CreateUserRequestDTO;
import com.Familyship.checkkuleogi.domains.user.dto.request.LoginUserRequestDTO;
import com.Familyship.checkkuleogi.domains.user.dto.response.CreateUserResponseDTO;
import com.Familyship.checkkuleogi.domains.user.dto.response.LoginUserResponseDTO;
import com.Familyship.checkkuleogi.domains.user.exception.UserException;
import com.Familyship.checkkuleogi.domains.user.exception.UserExceptionType;
import com.Familyship.checkkuleogi.global.domain.exception.NotFoundException;
import com.Familyship.checkkuleogi.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
@RequiredArgsConstructor
public class UserManager {
    private final UserRepository userRepository;

    public CreateUserResponseDTO createUser(CreateUserRequestDTO createUserRequest, PasswordEncoder passwordEncoder, LocalDate date) {
        SiteUser member = SiteUser.builder()
                .id(createUserRequest.getId())
                .password(passwordEncoder.encode(createUserRequest.getPassword()))
                .email(createUserRequest.getEmail())
                .role(Role.USER) // 기본 역할
                .name(createUserRequest.getName())
                .birth(date)
                .gender(createUserRequest.getGender())
                .build();

        userRepository.save(member);
        return CreateUserResponseDTO.builder()
                .message("회원가입 성공")
                .build();
    }

    public LoginUserResponseDTO loginUser(LoginUserRequestDTO loginUserRequest, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        SiteUser user = userRepository.findById(loginUserRequest.getId())
                .orElseThrow(() -> new NotFoundException("가입되지 않은 E-MAIL 입니다."));

        if (!passwordEncoder.matches(loginUserRequest.getPassword(), user.getPassword())) {
            throw new NotFoundException("잘못된 비밀번호입니다.");
        }

        String token = jwtProvider.createToken(user.getIdx(), user.getAuthorities());
        return LoginUserResponseDTO.builder()
                .token(token)
                .build();
    }

    public UserDto findUserById(Long id) {
        SiteUser user = userRepository.findById(id)
                .orElseThrow(() -> new UserException(UserExceptionType.USER_NOT_FOUND_EXCEPTION));
        return new UserDto(user.getIdx(), user.getName(), user.getEmail(), user.getRole());
    }
}
