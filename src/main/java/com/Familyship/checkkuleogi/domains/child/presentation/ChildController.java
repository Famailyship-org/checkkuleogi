package com.Familyship.checkkuleogi.domains.child.presentation;

import com.Familyship.checkkuleogi.domains.child.dto.*;
import com.Familyship.checkkuleogi.domains.child.service.ChildService;
import com.Familyship.checkkuleogi.global.domain.response.CommonResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.Familyship.checkkuleogi.global.domain.response.CommonResponseEntity.success;

@RestController
@RequestMapping("/api/v1/child")
@RequiredArgsConstructor
public class ChildController {

    private final ChildService childService;

    @PostMapping("/mbti")
    public CommonResponseEntity<CreateChildResponseDTO> createMBTI(@RequestBody CreateChildRequestDTO createChildRequestDTO) {
        return success(childService.createMBTI(createChildRequestDTO));
    }

    @GetMapping("/mbti")
    public CommonResponseEntity<ReadChildResponseDTO> getMBTI(@RequestBody ReadChildRequestDTO readChildRequestDTO) {
        return success(childService.readMBTI(readChildRequestDTO));
    }

    @DeleteMapping("/mbti/{childName}")
    public CommonResponseEntity<DeleteChildMBTIResponseDTO> deleteMBTI(@PathVariable String childName) {
        return success(childService.deleteMBTI(childName));
    }
}