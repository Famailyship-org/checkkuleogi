package com.Familyship.checkkuleogi.domains.user.presentation;

import com.Familyship.checkkuleogi.domains.user.dto.UserDto;
import com.Familyship.checkkuleogi.domains.user.dto.request.CreateUserRequestDTO;
import com.Familyship.checkkuleogi.domains.user.dto.request.LoginUserRequestDTO;
import com.Familyship.checkkuleogi.domains.user.dto.response.CreateUserResponseDTO;
import com.Familyship.checkkuleogi.domains.user.dto.response.LoginUserResponseDTO;

public interface UserUseCase {
    CreateUserResponseDTO join(CreateUserRequestDTO createUserRequest);
    LoginUserResponseDTO login(LoginUserRequestDTO loginUserRequest);
    UserDto findUserById(Long id);
}
