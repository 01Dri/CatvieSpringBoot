package me.dri.Catvie.infra.adapters;

import me.dri.Catvie.domain.adapters.services.auth.AuthenticationServiceImpl;
import me.dri.Catvie.domain.exceptions.auth.InvalidEmailLogin;
import me.dri.Catvie.domain.exceptions.auth.InvalidLoginPassword;
import me.dri.Catvie.domain.exceptions.user.AlreadyExistsUserException;
import me.dri.Catvie.domain.models.dto.auth.LoginDTO;
import me.dri.Catvie.domain.models.core.User;
import me.dri.Catvie.domain.ports.interfaces.auth.AuthenticationPort;
import me.dri.Catvie.domain.ports.interfaces.auth.TokenServicesPort;
import me.dri.Catvie.infra.entities.UserEntity;
import me.dri.Catvie.infra.repositoriesjpa.UserRepositoryJPA;
import me.dri.Catvie.infra.ports.auth.EncoderPassword;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationAdapter implements AuthenticationPort {

    private final TokenServicesPort tokenServicesPort;
    private final UserRepositoryJPA repositoryJPA;
    private final EncoderPassword passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final ModelMapper modelMapper;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);



    @Autowired
    public AuthenticationAdapter(TokenServicesPort tokenServicesPort, UserRepositoryJPA repositoryJPA, EncoderPassword passwordEncoder, AuthenticationManager authenticationManager, ModelMapper modelMapper) {
        this.tokenServicesPort = tokenServicesPort;
        this.repositoryJPA = repositoryJPA;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.modelMapper = modelMapper;
    }


    @Override
    public User register(User userRegister) {
        String passwordByUserEncrypted = this.passwordEncoder.encode(userRegister.getPassword());
        UserEntity createdNewUser = this.modelMapper.map(userRegister, UserEntity.class);
        this.verifyIfUserAlreadyExistsOnDatabaseByEmail(createdNewUser.getEmail());
        createdNewUser.setPassword(passwordByUserEncrypted);
        UserEntity userRegistered = repositoryJPA.save(createdNewUser);
        logger.info("User id: " +  userRegistered.getId() + " registered!");
        return this.modelMapper.map(userRegistered, User.class);

    }

    @Override
    public String login(LoginDTO user) {
        UserDetails userByEmail = this.repositoryJPA.findByEmail(user.email()).orElseThrow(
                () -> new InvalidEmailLogin("Not found user by email: " + user.email()));
        String token = this.tokenServicesPort.generateToken((UserEntity) userByEmail);
        ((UserEntity) userByEmail).setToken(token);
        UserEntity userLogged = repositoryJPA.save((UserEntity) userByEmail);
        var usernamePasswordAuthentication = new UsernamePasswordAuthenticationToken(user.email(), user.password());
        try {
            this.authenticationManager.authenticate(usernamePasswordAuthentication);
        } catch (AuthenticationException e) { // Password invalid
            throw  new InvalidLoginPassword("Not found user by password: " + user.password());
        }
        logger.info("User id: " +  userLogged.getId() + " authenticated!");

        return ((UserEntity) userByEmail).getToken();
    }
    private void verifyIfUserAlreadyExistsOnDatabaseByEmail(String email) {
        var entity = this.repositoryJPA.findByEmail(email);
        if (entity.isPresent()) {
            throw new AlreadyExistsUserException("User already exist!!!");
        }
    }

}
