package me.dri.Catvie.domain.models.dto.auth;

import me.dri.Catvie.domain.enums.UserRole;

public record RegisterDTO(String firstName, String lastName,
                          String email, String password, UserRole role) {
}


