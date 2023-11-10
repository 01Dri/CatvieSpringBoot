package me.dri.Catvie.domain.ports.interfaces;

import me.dri.Catvie.domain.models.dto.LoginDTO;
import me.dri.Catvie.domain.models.dto.RegisterDTO;
import me.dri.Catvie.domain.models.dto.RegisterResponseDTO;
import me.dri.Catvie.domain.models.dto.TokenResponseDTO;

public interface AuthenticationServicePort {

    RegisterResponseDTO register(RegisterDTO register);
    TokenResponseDTO login(LoginDTO login);
}
