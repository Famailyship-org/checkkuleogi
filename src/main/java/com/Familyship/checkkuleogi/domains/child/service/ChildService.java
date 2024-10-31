package com.Familyship.checkkuleogi.domains.child.service;

import com.Familyship.checkkuleogi.domains.child.dto.request.*;
import com.Familyship.checkkuleogi.domains.child.dto.response.*;

public interface ChildService {

    CreateChildResponseMbtiDTO createMBTI(CreateChildRequestMbtiDTO childCreateRequestDTO);

    CreateChildResponseDTO createChild(CreateChildRequestDTO createChildRequestDTO, String token);

    ReadChildResponseDTO readMBTI(ReadChildRequestDTO readChildRequestDTO);

    LoginChildRequestDTO loginChild(String token);

    void deleteMBTI(DeleteChildMBTIRequestDTO deleteChildMBTIRequestDTO);

    UpdateChildMBTIResponseDTO updateMBTI(UpdateChildMBTIRequestDTO updateChildMBTIRequestDTO);

    ChildResponse getChildById(Long childIdx);

}
