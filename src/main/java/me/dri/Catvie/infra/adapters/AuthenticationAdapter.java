package me.dri.Catvie.infra.adapters;

import me.dri.Catvie.domain.models.dto.auth.LoginDTO;
import me.dri.Catvie.domain.models.dto.auth.TokenResponseDTO;
import me.dri.Catvie.domain.models.entities.User;
import me.dri.Catvie.domain.ports.interfaces.auth.AuthenticationPort;
import me.dri.Catvie.domain.ports.interfaces.auth.TokenServicesPort;
import me.dri.Catvie.infra.entities.UserEntity;
import me.dri.Catvie.infra.ports.EncoderPassword;
import me.dri.Catvie.infra.ports.MapperUserPort;
import me.dri.Catvie.infra.ports.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationAdapter implements AuthenticationPort {

    private final TokenServicesPort tokenServicesPort;
    private final UserRepositoryJPA repositoryJPA;

    private final MapperUserPort mapperUserPort;

    private final EncoderPassword passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationAdapter(TokenServicesPort tokenServicesPort, UserRepositoryJPA repositoryJPA, MapperUserPort mapperUserPort, EncoderPassword passwordEncoder, AuthenticationManager authenticationManager) {
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
        this.repositoryJPA.save(user);
    }

    @Override
    public TokenResponseDTO login(LoginDTO user) {
        var userLogin = this.repositoryJPA.findByEmail(user.email());
        var token = new TokenResponseDTO(this.tokenServicesPort.generateToken((UserEntity) userLogin));
        ((UserEntity) userLogin).setToken(token.token());
        this.repositoryJPA.save((UserEntity) userLogin);
        var usernamePassword = new UsernamePasswordAuthenticationToken(user.email(), user.password());
        this.authenticationManager.authenticate(usernamePassword);
        return new TokenResponseDTO(((UserEntity) userLogin).getToken());
    }
}
