package me.dri.Catvie.domain.ports.interfaces;

import me.dri.Catvie.domain.models.dto.LoginDTO;
import me.dri.Catvie.domain.models.dto.TokenResponseDTO;
import me.dri.Catvie.domain.models.entities.User;

public interface AuthenticationPort {
    void register(User register);
    TokenResponseDTO login(LoginDTO user);
}
