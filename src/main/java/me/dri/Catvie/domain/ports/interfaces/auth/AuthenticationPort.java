package me.dri.Catvie.domain.ports.interfaces.auth;

import me.dri.Catvie.domain.models.dto.auth.LoginDTO;
import me.dri.Catvie.domain.models.core.User;

public interface AuthenticationPort {
    User register(User register);
    String login(LoginDTO user);
}
