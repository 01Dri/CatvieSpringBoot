package me.dri.Catvie.domain.models.dto;

import me.dri.Catvie.domain.enums.UserRole;

public record UserResponseDTO(String name, String firstName, String email, UserRole role) {
}
