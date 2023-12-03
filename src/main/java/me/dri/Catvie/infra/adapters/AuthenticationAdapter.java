package me.dri.Catvie.infra.adapters;

import me.dri.Catvie.domain.exceptions.auth.InvalidEmailLogin;
import me.dri.Catvie.domain.exceptions.auth.InvalidLoginPassword;
import me.dri.Catvie.domain.exceptions.user.AlreadyExistsUserException;
import me.dri.Catvie.domain.models.dto.auth.LoginDTO;
import me.dri.Catvie.domain.models.entities.User;
import me.dri.Catvie.domain.ports.interfaces.auth.AuthenticationPort;
import me.dri.Catvie.domain.ports.interfaces.auth.TokenServicesPort;
import me.dri.Catvie.infra.entities.UserEntity;
import me.dri.Catvie.infra.ports.auth.EncoderPassword;
import me.dri.Catvie.infra.ports.mappers.MapperUserInfraPort;
import me.dri.Catvie.infra.jpa.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationAdapter implements AuthenticationPort {

    private final TokenServicesPort tokenServicesPort;
    private final UserRepositoryJPA repositoryJPA;

    private final MapperUserInfraPort mapperUserPort;
    private final EncoderPassword passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationAdapter(TokenServicesPort tokenServicesPort, UserRepositoryJPA repositoryJPA, MapperUserInfraPort mapperUserPort, EncoderPassword passwordEncoder, AuthenticationManager authenticationManager) {
        this.tokenServicesPort = tokenServicesPort;
        this.repositoryJPA = repositoryJPA;
        this.mapperUserPort = mapperUserPort;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void register(User register) {
        String password = this.passwordEncoder.encode(register.getPassword());
        var user = this.mapperUserPort.convertUserToUserEntity(register, password);
        this.verifyIfUserAlreadyExists(user.getEmail());
        this.repositoryJPA.save(user);
    }

    @Override
    public String login(LoginDTO user) {
        var userLoginByEmail = this.repositoryJPA.findByEmail(user.email()).orElseThrow(
                () -> new InvalidEmailLogin("Not found user by email: " + user.email()));

        String token = this.tokenServicesPort.generateToken((UserEntity) userLoginByEmail);
        ((UserEntity) userLoginByEmail).setToken(token);
        this.repositoryJPA.save((UserEntity) userLoginByEmail);
        var usernamePassword = new UsernamePasswordAuthenticationToken(user.email(), user.password());
        try {
            this.authenticationManager.authenticate(usernamePassword);
        } catch (AuthenticationException e) { // Password invalid
            throw  new InvalidLoginPassword("Not found user by password: " + user.password());
        }
        return ((UserEntity) userLoginByEmail).getToken();
    }

    private void verifyIfUserAlreadyExists(String email) {
        var entity = this.repositoryJPA.findByEmail(email);
        if (entity.isPresent()) {
            throw new AlreadyExistsUserException("User already exist!!!");
        }
    }

}
