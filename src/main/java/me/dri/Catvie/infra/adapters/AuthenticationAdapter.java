package me.dri.Catvie.infra.adapters;

import me.dri.Catvie.domain.models.dto.TokenResponseDTO;
import me.dri.Catvie.domain.models.entities.User;
import me.dri.Catvie.domain.ports.interfaces.AuthenticationPort;
import me.dri.Catvie.domain.ports.interfaces.TokenServicesPort;
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
    private final UserRepositoryJPA repositoryPort;

    private final MapperUserPort mapperUserPort;

    private final EncoderPassword passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationAdapter(TokenServicesPort tokenServicesPort, UserRepositoryJPA repositoryPort, MapperUserPort mapperUserPort, EncoderPassword passwordEncoder, AuthenticationManager authenticationManager) {
        this.tokenServicesPort = tokenServicesPort;
        this.repositoryPort = repositoryPort;
        this.mapperUserPort = mapperUserPort;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void register(User register) {
        var user = this.mapperUserPort.convertUserToUserEntity(register, this.passwordEncoder.encode(register.getPassword()));
        this.repositoryPort.save(user);
    }

    @Override
    public void login(User user) {
        var userLogin = this.repositoryPort.findByEmail(user.getEmail());
        var token = new TokenResponseDTO(this.tokenServicesPort.generateToken((UserEntity) userLogin));
        ((UserEntity) userLogin).setToken(token.token());
        this.repositoryPort.save((UserEntity) userLogin);

        var usernamePassword = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        this.authenticationManager.authenticate(usernamePassword);
    }
}
