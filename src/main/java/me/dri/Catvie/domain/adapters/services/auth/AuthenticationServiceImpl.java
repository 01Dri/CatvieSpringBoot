package me.dri.Catvie.domain.adapters.services.auth;

import me.dri.Catvie.domain.exceptions.validations.AuthValidations;
import me.dri.Catvie.domain.models.dto.auth.LoginDTO;
import me.dri.Catvie.domain.models.dto.auth.RegisterDTO;
import me.dri.Catvie.domain.models.dto.auth.RegisterResponseDTO;
import me.dri.Catvie.domain.models.dto.auth.TokenResponseDTO;
import me.dri.Catvie.domain.models.core.User;
import me.dri.Catvie.domain.ports.interfaces.auth.AuthenticationPort;
import me.dri.Catvie.domain.ports.interfaces.auth.AuthenticationServicePort;
import me.dri.Catvie.domain.ports.interfaces.mappers.MapperUserResponsePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthenticationServiceImpl implements AuthenticationServicePort {

    private final AuthenticationPort authenticationPort;

    private final MapperUserResponsePort mapperUserPort;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    public AuthenticationServiceImpl(AuthenticationPort authenticationPort, MapperUserResponsePort mapperUserPort) {
        this.authenticationPort = authenticationPort;
        this.mapperUserPort = mapperUserPort;
    }



    @Override
    public RegisterResponseDTO register(RegisterDTO register) throws NoSuchFieldException, IllegalAccessException {
        AuthValidations.validateRegisterDTO(register);
        User user = this.mapperUserPort.convertRegisterDTOToUser(register);
        this.authenticationPort.register(user);
        logger.info("User registered!!!");
        return new RegisterResponseDTO(user.getFirstName(), user.getLastName(), user.getEmail());
    }

    @Override
    public TokenResponseDTO login(LoginDTO login) throws NoSuchFieldException, IllegalAccessException {
        AuthValidations.validateLoginDTO(login);
        var token = this.authenticationPort.login(login);
        logger.info("User logged!!!");
        return new TokenResponseDTO(token);

    }

}
