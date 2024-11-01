package com.Familyship.checkkuleogi.domains.child.presentation;

import com.Familyship.checkkuleogi.domains.child.dto.request.*;
import com.Familyship.checkkuleogi.domains.child.dto.response.*;
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
    public CommonResponseEntity<CreateChildResponseMbtiDTO> createMBTI(@RequestBody CreateChildRequestMbtiDTO createChildRequestMBTIDTO) {
        return success(childService.createMBTI(createChildRequestMBTIDTO));
    }
    @PostMapping("")
    public CommonResponseEntity<CreateChildResponseDTO> create(
            @RequestHeader("Authorization") String token,
            @RequestBody CreateChildRequestDTO createChildRequestDTO) {
        return success(childService.createChild(createChildRequestDTO, token));
    }

    @PostMapping("/login")
    public CommonResponseEntity<LoginChildRequestDTO> loginChild (@RequestHeader("Authorization") String token) {
        return  success(childService.loginChild(token));
    }
    @GetMapping("/mbti")
    public CommonResponseEntity<ReadChildResponseDTO> getMBTI(@RequestBody ReadChildRequestDTO readChildRequestDTO) {
        return success(childService.readMBTI(readChildRequestDTO));
    }

    @DeleteMapping("/mbti")
    public CommonResponseEntity deleteMBTI(@RequestBody DeleteChildMBTIRequestDTO deleteChildMBTIRequestDTO) {
        childService.deleteMBTI(deleteChildMBTIRequestDTO);
        return success(true);
    }

    @PatchMapping("/mbti")
    public CommonResponseEntity<UpdateChildMBTIResponseDTO> updateMBTI(@RequestBody UpdateChildMBTIRequestDTO updateChildMBTIRequestDTO) {
        return success(childService.updateMBTI(updateChildMBTIRequestDTO));
    }

    @GetMapping("/{childIdx}")
    public CommonResponseEntity<ChildResponse> getChildById(@PathVariable Long childIdx) {
        return success(childService.getChildById(childIdx));
    }

    @GetMapping("mbti/logs/{childIdx}")
    public CommonResponseEntity<GetChildMbtiLogsResponseDTO> getMbtiLogs(@PathVariable Long childIdx) {
        return success(childService.getMbtiLogs(childIdx));
    }
}