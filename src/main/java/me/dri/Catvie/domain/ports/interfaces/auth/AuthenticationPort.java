package me.dri.Catvie.domain.ports.interfaces.auth;

import me.dri.Catvie.domain.models.dto.auth.LoginDTO;
import me.dri.Catvie.domain.models.dto.auth.TokenResponseDTO;
import me.dri.Catvie.domain.models.entities.User;

public interface AuthenticationPort {
    void register(User register);
    String login(LoginDTO user);
}
