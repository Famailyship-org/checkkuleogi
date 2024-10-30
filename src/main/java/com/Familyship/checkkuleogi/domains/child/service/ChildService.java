package com.Familyship.checkkuleogi.domains.child.service;

import com.Familyship.checkkuleogi.domains.child.dto.*;

public interface ChildService {

    CreateChildResponseMbtiDTO createMBTI(CreateChildRequestMbtiDTO childCreateRequestDTO);

    CreateChildResponseDTO createChild(CreateChildRequestDTO createChildRequestDTO, String token);

    ReadChildResponseDTO readMBTI(ReadChildRequestDTO readChildRequestDTO);

    LoginChildRequestDTO loginChild(String token);

    void deleteMBTI(DeleteChildMBTIRequestDTO deleteChildMBTIRequestDTO);

    UpdateChildMBTIResponseDTO updateMBTI(UpdateChildMBTIRequestDTO updateChildMBTIRequestDTO);
}
