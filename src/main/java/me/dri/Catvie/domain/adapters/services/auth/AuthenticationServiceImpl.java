package me.dri.Catvie.domain.adapters.services.auth;

import me.dri.Catvie.domain.exceptions.auth.*;
import me.dri.Catvie.domain.models.dto.auth.LoginDTO;
import me.dri.Catvie.domain.models.dto.auth.RegisterDTO;
import me.dri.Catvie.domain.models.dto.auth.RegisterResponseDTO;
import me.dri.Catvie.domain.models.dto.auth.TokenResponseDTO;
import me.dri.Catvie.domain.models.entities.User;
import me.dri.Catvie.domain.ports.interfaces.auth.AuthenticationPort;
import me.dri.Catvie.domain.ports.interfaces.auth.AuthenticationServicePort;
import me.dri.Catvie.domain.ports.interfaces.user.MapperUserPort;
import me.dri.Catvie.domain.ports.repositories.UserRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthenticationServiceImpl implements AuthenticationServicePort {

    private final AuthenticationPort authenticationPort;

    private final MapperUserPort mapperUserPort;

    private final UserRepositoryPort repositoryPort;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    public AuthenticationServiceImpl(AuthenticationPort authenticationPort, MapperUserPort mapperUserPort, UserRepositoryPort repositoryPort) {
        this.authenticationPort = authenticationPort;
        this.mapperUserPort = mapperUserPort;
        this.repositoryPort = repositoryPort;
    }

    @Override
    public RegisterResponseDTO register(RegisterDTO register) {
        this.isValidUserRegister(register);
        User user = this.mapperUserPort.convertRegisterDTOToUser(register);
        this.authenticationPort.register(user);
        logger.info("User registered!!!");
        return new RegisterResponseDTO(user.getFirstName(), user.getLastName(), user.getEmail());
    }

    @Override
    public TokenResponseDTO login(LoginDTO login) {
        this.isValidUserLogin(login);
        var token = this.authenticationPort.login(login);
        logger.info("User logged!!!");
        return new TokenResponseDTO(token.token());

    }


    public void isValidUserLogin(LoginDTO loginDTO) {

        try {
            if (loginDTO.email().isBlank() || loginDTO.email().isEmpty()) {
                throw new MissingInformationEmail("Content 'email' is empty");
            }
        } catch (NullPointerException e) {
            throw  new MissingInformationEmail("Content 'email' is null");
        }

        if (loginDTO.email().contains(" ")) {
            throw new InvalidCharacterEmail("Content 'email' contains a character invalid");
        }

        try {
            if (loginDTO.password().isBlank() || loginDTO.password().isEmpty()) {
                throw new MissingInformationPassword("Content 'password' is empty");
            }
        } catch (NullPointerException e) {
            throw  new MissingInformationPassword("Content 'password' is null");
        }

        if (loginDTO.password().contains(" ")) {
            throw new CharacterInvalidInPassword("Content 'password' contains a character invalid");
        }
    }
    public void isValidUserRegister(RegisterDTO registerDTO) {
        try {
            if (registerDTO.email().isEmpty() || registerDTO.email().isBlank()) {
                logger.error("Email user is empty");
                throw new MissingInformationEmail("Content 'email' is empty");
            }
        } catch (NullPointerException e) {
            logger.error("Email user is null");
            throw new MissingInformationEmail("Content 'email' is null");
        }

        if (registerDTO.email().contains(" ")) {
            logger.error("Email user contains invalid character");
            throw new InvalidCharacterEmail("Content 'email' contains a character invalid");
        }
        try {
            if (registerDTO.firstName().isEmpty() || registerDTO.firstName().isBlank()) {
                logger.error("First name user is empty");
                throw new MissingInformationFirstName("Content 'firstname' is empty");
            }
        } catch (NullPointerException e) {
            logger.error("First name user is null");
            throw new MissingInformationFirstName("Content 'firstname' is null");

        }
        try {
            if (registerDTO.lastName().isEmpty() || registerDTO.lastName().isBlank()) {
                throw new MissingInformationLastName("Content 'lastname' is empty");
            }
        } catch (NullPointerException e) {
            logger.error("Last name user is null");
            throw new MissingInformationLastName("Content 'lastname' is null");
        }

        try {
            if (registerDTO.password().isEmpty() || registerDTO.password().isBlank()) {
                logger.error("Password user is empty");
                throw new MissingInformationPassword("Content 'password' is empty");
            }
        } catch (NullPointerException e) {
            logger.error("Password user is null");
            throw new MissingInformationPassword("Content 'password' is null");
        }

        if (registerDTO.password().length() < 8) {
            logger.error("Invalid length for password user");
            throw  new PasswordLengthInvalid("Content 'password' must contain length '8'");

        }

        if (registerDTO.password().contains(" ")) {
            logger.error("Password invalid character");
            throw new CharacterInvalidInPassword("Content 'password' contains invalid character");
        }

        try {
            if (registerDTO.role().name().isBlank() || registerDTO.role().name().isEmpty()) {
                logger.error("Role user is empty");
                throw new MissingInformationRole("Content 'role' is empty");
            }
        } catch (NullPointerException e) {
                logger.error("Role user is null");
                throw  new MissingInformationRole("Content 'role' is null");
        }

        try {
            if (!registerDTO.role().name().equals("USER") && !registerDTO.role().name().equals("ADMIN")) {
                logger.error("Role user is invalid");
                throw new NameRoleInvalid("Content 'role' " + "[" + registerDTO.role().name() + "] is invalid");
            }
        } catch (IllegalArgumentException e) {
            logger.error("Role user is invalid");
            throw new NameRoleInvalid("Content 'role' + [ " + registerDTO.role() + "] is invalid!");
        }

    }
}
