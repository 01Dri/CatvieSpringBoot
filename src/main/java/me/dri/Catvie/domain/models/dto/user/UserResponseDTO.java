package me.dri.Catvie.domain.models.dto.user;

import jakarta.xml.bind.annotation.XmlRootElement;
import me.dri.Catvie.domain.enums.UserRole;


@XmlRootElement
public record UserResponseDTO(Long id, String firstName, String lastName, String email, UserRole role) {
}
