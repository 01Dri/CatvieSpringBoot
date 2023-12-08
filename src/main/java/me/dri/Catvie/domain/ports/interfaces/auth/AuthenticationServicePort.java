package me.dri.Catvie.domain.ports.interfaces.auth;

import me.dri.Catvie.domain.models.dto.auth.LoginDTO;
import me.dri.Catvie.domain.models.dto.auth.RegisterDTO;
import me.dri.Catvie.domain.models.dto.auth.RegisterResponseDTO;
import me.dri.Catvie.domain.models.dto.auth.TokenResponseDTO;

public interface AuthenticationServicePort {

    RegisterResponseDTO register(RegisterDTO register);
    TokenResponseDTO login(LoginDTO login);
}
