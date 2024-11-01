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
import com.Familyship.checkkuleogi.domains.user.implementation.mapper.UserDtoMapper;
import com.Familyship.checkkuleogi.global.domain.exception.NotFoundException;
import com.Familyship.checkkuleogi.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Component
@RequiredArgsConstructor
public class UserManager {
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    public CreateUserResponseDTO createUser(CreateUserRequestDTO createUserRequest, PasswordEncoder passwordEncoder) {
        LocalDate date = LocalDate.parse(createUserRequest.getBirthday(), DateTimeFormatter.ISO_LOCAL_DATE); // date 처리
        SiteUser member = userDtoMapper.toSiteUser(createUserRequest, passwordEncoder, date); // 매퍼 수정

        userRepository.save(member);
        return userDtoMapper.toCreateUserResponse("회원가입 성공");
    }

    public LoginUserResponseDTO loginUser(LoginUserRequestDTO loginUserRequest, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        SiteUser user = userRepository.findById(loginUserRequest.getId())
                .orElseThrow(() -> new NotFoundException("가입되지 않은 E-MAIL 입니다."));

        if (!passwordEncoder.matches(loginUserRequest.getPassword(), user.getPassword())) {
            throw new NotFoundException("잘못된 비밀번호입니다.");
        }

        String token = jwtProvider.createToken(user.getIdx(), user.getAuthorities());
        return userDtoMapper.toLoginUserResponse(token);
    }

    public UserDto findUserById(Long id) {
        SiteUser user = userRepository.findById(id)
                .orElseThrow(() -> new UserException(UserExceptionType.USER_NOT_FOUND_EXCEPTION));
        return userDtoMapper.toUserDto(user);
    }
}
