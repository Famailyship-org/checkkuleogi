package com.Familyship.checkkuleogi.domains.child.presentation;

import com.Familyship.checkkuleogi.domains.child.dto.request.*;
import com.Familyship.checkkuleogi.domains.child.dto.response.*;
import com.Familyship.checkkuleogi.global.presentation.CommonResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.Familyship.checkkuleogi.global.presentation.CommonResponseEntity.success;

@RestController
@RequestMapping("/api/v1/child")
@RequiredArgsConstructor
public class ChildController {

    private final ChildUseCase childUseCase;

    @PostMapping("/mbti")
    public CommonResponseEntity<CreateChildResponseMbtiDTO> createMBTI(@RequestBody CreateChildRequestMbtiDTO createChildRequestMBTIDTO) {
        return success(childUseCase.createMBTI(createChildRequestMBTIDTO));
    }
    @PostMapping("")
    public CommonResponseEntity<CreateChildResponseDTO> create(
            @RequestHeader("Authorization") String token,
            @RequestBody CreateChildRequestDTO createChildRequestDTO) {
        return success(childUseCase.createChild(createChildRequestDTO, token));
    }

    @PostMapping("/login")
    public CommonResponseEntity<LoginChildRequestDTO> loginChild (@RequestHeader("Authorization") String token) {
        return  success(childUseCase.loginChild(token));
    }
    @GetMapping("/mbti")
    public CommonResponseEntity<ReadChildResponseDTO> getMBTI(@RequestParam Long childIdx) {
        return success(childUseCase.readMBTI(ReadChildRequestDTO.builder().childIdx(childIdx).build()));
    }


    @DeleteMapping("/mbti")
    public CommonResponseEntity deleteMBTI(@RequestBody DeleteChildMBTIRequestDTO deleteChildMBTIRequestDTO) {
        childUseCase.deleteMBTI(deleteChildMBTIRequestDTO);
        return success(true);
    }

    @PatchMapping("/mbti")
    public CommonResponseEntity<UpdateChildMBTIResponseDTO> updateMBTI(@RequestBody UpdateChildMBTIRequestDTO updateChildMBTIRequestDTO) {
        return success(childUseCase.updateMBTI(updateChildMBTIRequestDTO));
    }

    @GetMapping("/{childIdx}")
    public CommonResponseEntity<ChildResponse> getChildById(@PathVariable Long childIdx) {
        return success(childUseCase.getChildById(childIdx));
    }

    @GetMapping("mbti/logs/{childIdx}")
    public CommonResponseEntity<GetChildMbtiLogsResponseDTO> getMbtiLogs(@PathVariable Long childIdx) {
        return success(childUseCase.getMbtiLogs(childIdx));
    }
}