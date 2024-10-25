package com.Familyship.checkkuleogi.domains.test.service;

import com.Familyship.checkkuleogi.domains.test.domain.repository.Test;
import com.Familyship.checkkuleogi.domains.test.domain.repository.TestRepository;
import com.Familyship.checkkuleogi.domains.test.dto.request.Test1RequestDTO;
import com.Familyship.checkkuleogi.domains.test.dto.response.Test1ResponseDto;
import com.Familyship.checkkuleogi.domains.test.dto.response.Test2ResponseDto;
import com.Familyship.checkkuleogi.domains.user.domain.repository.UserRepository;
import com.Familyship.checkkuleogi.security.jwt.JwtProvider;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {

    private final JwtProvider jwtProvider;
    private final TestRepository testRepository;

    public Test1ResponseDto checkTokenTest(Test1RequestDTO requestDTO) {
        String message = requestDTO.getMessage();
        String newMessage = "new message "+message;
        Long idx = requestDTO.getIdx();
        return Test1ResponseDto.builder()
                .idx(idx)
                .message(newMessage)
                .build();
    }

    public Test2ResponseDto getName(Long idx){
        Test test = testRepository.findByIdx(idx);
        String userName = test.getName();
        System.out.println("userName is:"+userName);
        return Test2ResponseDto.builder().
                name(userName).
                build();
    }
}
