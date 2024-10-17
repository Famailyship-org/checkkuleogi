package com.Familyship.checkkuleogi.domains.child.service;

import com.Familyship.checkkuleogi.domains.child.dto.*;

public interface ChildService {

    CreateChildResponseDTO createMBTI(CreateChildRequestDTO childCreateRequestDTO);

    ReadChildResponseDTO readMBTI(ReadChildRequestDTO readChildRequestDTO);

    DeleteChildMBTIResponseDTO deleteMBTI(String childName);
}
