package me.dri.Catvie.domain.adapters.services;

import me.dri.Catvie.domain.models.dto.auth.LoginDTO;
import me.dri.Catvie.domain.models.dto.auth.RegisterDTO;
import me.dri.Catvie.domain.models.dto.auth.RegisterResponseDTO;
import me.dri.Catvie.domain.models.dto.auth.TokenResponseDTO;
import me.dri.Catvie.domain.models.entities.User;
import me.dri.Catvie.domain.ports.interfaces.auth.AuthenticationPort;
import me.dri.Catvie.domain.ports.interfaces.auth.AuthenticationServicePort;
import me.dri.Catvie.domain.ports.interfaces.user.MapperUserPort;
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
        return new RegisterResponseDTO(user.getFirstName(), user.getLastName(), user.getEmail(), user.getToken());
    }

    @Override
    public TokenResponseDTO login(LoginDTO login) {
        var token = this.authenticationPort.login(login);
        return new TokenResponseDTO(token.token());

    }
}
