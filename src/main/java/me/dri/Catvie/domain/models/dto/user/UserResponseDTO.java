package me.dri.Catvie.domain.models.dto.user;

import me.dri.Catvie.domain.enums.UserRole;

public record UserResponseDTO(Long id, String firstName, String lastName, String email, UserRole role) {
}
