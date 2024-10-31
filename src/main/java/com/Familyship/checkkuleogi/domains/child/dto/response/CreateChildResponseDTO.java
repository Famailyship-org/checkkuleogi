package com.Familyship.checkkuleogi.domains.child.dto.response;

import java.time.LocalDate;

public record CreateChildResponseDTO(Long idx,String name, String gender, Integer age, LocalDate birth) {
}