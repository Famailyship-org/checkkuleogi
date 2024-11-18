package com.Familyship.checkkuleogi.child.dto.response;

import java.time.LocalDate;

public record CreateChildResponseDTO(Long idx,String name, String gender, Integer age, LocalDate birth) {
}