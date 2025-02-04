package com.Familyship.checkkuleogi.user.presentation;

import com.Familyship.checkkuleogi.user.dto.UserDto;
import com.Familyship.checkkuleogi.user.dto.request.CreateUserRequestDTO;
import com.Familyship.checkkuleogi.user.dto.request.LoginUserRequestDTO;
import com.Familyship.checkkuleogi.user.dto.response.CreateUserResponseDTO;
import com.Familyship.checkkuleogi.user.dto.response.LoginUserResponseDTO;
import com.Familyship.checkkuleogi.user.service.UserService;

import com.Familyship.checkkuleogi.global.presentation.CommonResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.Familyship.checkkuleogi.global.presentation.CommonResponseEntity.success;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public CommonResponseEntity<CreateUserResponseDTO> join(@RequestBody CreateUserRequestDTO user) {
        return success(userService.join(user));
    }

    @PostMapping("/login")
    public CommonResponseEntity<LoginUserResponseDTO> login(@RequestBody LoginUserRequestDTO user) {
        return success(userService.login(user));
    }

    @GetMapping("/{id}")
    public CommonResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return success(userService.findUserById(id)); // 성공 응답 반환
    }

}