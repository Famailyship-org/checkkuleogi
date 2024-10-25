package com.Familyship.checkkuleogi.domains.test.presentation;

import com.Familyship.checkkuleogi.domains.test.dto.request.Test1RequestDTO;
import com.Familyship.checkkuleogi.domains.test.dto.response.Test1ResponseDto;
import com.Familyship.checkkuleogi.domains.test.dto.response.Test2ResponseDto;
import com.Familyship.checkkuleogi.domains.test.service.TestService;
import com.Familyship.checkkuleogi.global.domain.exception.BadRequestException;
import com.Familyship.checkkuleogi.global.domain.response.CommonResponseEntity;

import com.Familyship.checkkuleogi.security.jwt.JwtProvider;


import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import static com.Familyship.checkkuleogi.global.domain.response.CommonResponseEntity.success;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class TestController {

    private final TestService testService;
    private final JwtProvider jwtProvider;

    @PostMapping("/test1")
    public CommonResponseEntity<Test1ResponseDto> getTest1(@RequestHeader("Authorization") String token,
                                                           @RequestBody Test1RequestDTO requestDTO) {
        Long idx = Long.valueOf(jwtProvider.getUserIdFromToken(token));

        requestDTO.setIdx(idx);

        return success(testService.checkTokenTest(requestDTO));

    }

    @GetMapping("test2")
    public CommonResponseEntity<Test2ResponseDto> getTest2(@RequestHeader("Authorization") String token) {

        Long idx = Long.valueOf(jwtProvider.getUserIdFromToken(token));

        return success(testService.getName(idx));

    }
}
