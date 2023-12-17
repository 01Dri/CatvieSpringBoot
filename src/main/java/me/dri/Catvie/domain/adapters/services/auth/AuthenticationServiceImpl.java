package me.dri.Catvie.domain.adapters.services.auth;

import me.dri.Catvie.domain.exceptions.validations.AuthValidations;
import me.dri.Catvie.domain.models.core.User;
import me.dri.Catvie.domain.models.dto.auth.LoginDTO;
import me.dri.Catvie.domain.models.dto.auth.RegisterDTO;
import me.dri.Catvie.domain.models.dto.auth.RegisterResponseDTO;
import me.dri.Catvie.domain.models.dto.auth.TokenResponseDTO;
import me.dri.Catvie.domain.ports.interfaces.auth.AuthenticationPort;
import me.dri.Catvie.domain.ports.interfaces.auth.AuthenticationServicePort;
import me.dri.Catvie.domain.ports.interfaces.mappers.MapperUserResponsePort;

public class AuthenticationServiceImpl implements AuthenticationServicePort {

    private final AuthenticationPort authenticationPort;

    private final MapperUserResponsePort mapperUserPort;

    public AuthenticationServiceImpl(AuthenticationPort authenticationPort, MapperUserResponsePort mapperUserPort) {
        this.authenticationPort = authenticationPort;
        this.mapperUserPort = mapperUserPort;
    }



    @Override
    public RegisterResponseDTO register(RegisterDTO register) throws NoSuchFieldException, IllegalAccessException {
        AuthValidations.validateRegisterDTO(register); // This is responsible for validation and throwing exceptions
        User user = this.mapperUserPort.convertRegisterDTOToUser(register);
        User userByInfra = this.authenticationPort.register(user);
        return new RegisterResponseDTO(userByInfra.getId(), userByInfra.getFirstName(), userByInfra.getLastName(), userByInfra.getEmail());
    }

    @Override
    public TokenResponseDTO login(LoginDTO login) throws NoSuchFieldException, IllegalAccessException {
        AuthValidations.validateLoginDTO(login); // This is responsible for validation and throwing exceptions
        var token = this.authenticationPort.login(login);
        return new TokenResponseDTO(token);

    }

}
