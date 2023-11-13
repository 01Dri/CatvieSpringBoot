package me.dri.Catvie.domain.adapters.services;

import me.dri.Catvie.domain.models.dto.LoginDTO;
import me.dri.Catvie.domain.models.dto.RegisterDTO;
import me.dri.Catvie.domain.models.dto.RegisterResponseDTO;
import me.dri.Catvie.domain.models.dto.TokenResponseDTO;
import me.dri.Catvie.domain.models.entities.User;
import me.dri.Catvie.domain.ports.interfaces.AuthenticationPort;
import me.dri.Catvie.domain.ports.interfaces.AuthenticationServicePort;
import me.dri.Catvie.domain.ports.interfaces.MapperUserPort;
import me.dri.Catvie.domain.ports.repositories.UserRepositoryPort;

public class AuthenticationServiceImpl implements AuthenticationServicePort {

    private final AuthenticationPort authenticationPort;

    private final MapperUserPort mapperUserPort;

    private final UserRepositoryPort repositoryPort;

    public AuthenticationServiceImpl(AuthenticationPort authenticationPort, MapperUserPort mapperUserPort, UserRepositoryPort repositoryPort) {
        this.authenticationPort = authenticationPort;
        this.mapperUserPort = mapperUserPort;
        this.repositoryPort = repositoryPort;
    }

    @Override
    public RegisterResponseDTO register(RegisterDTO register) {
        User user = this.mapperUserPort.convertRegisterDTOToUser(register);
        this.authenticationPort.register(user);
        return new RegisterResponseDTO(user.getEmail());
    }

    @Override
    public TokenResponseDTO login(LoginDTO login) {
        var user = this.mapperUserPort.convertLoginDTOToUser(login);
        this.authenticationPort.login(user);
        return new TokenResponseDTO(user.getToken());

    }
}
