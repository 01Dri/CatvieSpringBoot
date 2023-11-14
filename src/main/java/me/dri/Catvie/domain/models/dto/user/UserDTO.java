package me.dri.Catvie.domain.models.dto.user;

import me.dri.Catvie.domain.enums.UserRole;

public record UserDTO(String firstName, String lastName, String email, String password, String token, UserRole role) {
}



