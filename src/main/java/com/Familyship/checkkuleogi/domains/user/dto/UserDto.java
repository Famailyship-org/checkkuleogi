package com.Familyship.checkkuleogi.domains.user.dto;

import com.Familyship.checkkuleogi.domains.user.domain.enums.Role;

public record UserDto(Long idx, String name, String email, Role role) {}


