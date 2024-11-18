package com.Familyship.checkkuleogi.child.service;

import com.Familyship.checkkuleogi.child.dto.request.*;
import com.Familyship.checkkuleogi.child.dto.response.*;

import com.Familyship.checkkuleogi.child.presentation.ChildUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChildService implements ChildUseCase {

    @Override
    public CreateChildResponseMbtiDTO createMBTI(CreateChildRequestMbtiDTO childCreateRequestDTO) {
        return null;
    }

    @Override
    public CreateChildResponseDTO createChild(CreateChildRequestDTO createChildRequestDTO, String token) {
        return null;
    }

    @Override
    public ReadChildResponseDTO readMBTI(ReadChildRequestDTO readChildRequestDTO) {
        return null;
    }

    @Override
    public LoginChildRequestDTO loginChild(String token) {
        return null;
    }

    @Override
    public void deleteMBTI(DeleteChildMBTIRequestDTO deleteChildMBTIRequestDTO) {

    }

    @Override
    public UpdateChildMBTIResponseDTO updateMBTI(UpdateChildMBTIRequestDTO updateChildMBTIRequestDTO) {
        return null;
    }

    @Override
    public ChildResponse getChildById(Long childIdx) {
        return null;
    }

    @Override
    public GetChildMbtiLogsResponseDTO getMbtiLogs(Long childIdx) {
        return null;
    }
}
