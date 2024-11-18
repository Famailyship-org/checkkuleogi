package com.Familyship.checkkuleogi.user.dto;

import com.Familyship.checkkuleogi.user.jpa.enums.Role;

public record UserDto(Long idx, String name, String email, Role role) {}


