package com.Familyship.checkkuleogi.domains.child.dto;

import com.Familyship.checkkuleogi.domains.user.domain.SiteUser;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

public record CreateChildResponseDTO(Long idx,String name, String gender, Integer age, LocalDate birth) {
}