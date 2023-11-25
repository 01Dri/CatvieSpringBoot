package me.dri.Catvie.unittest.authtests;

import me.dri.Catvie.domain.exceptions.auth.InvalidEmailLogin;
import me.dri.Catvie.domain.exceptions.auth.InvalidLoginPassword;
import me.dri.Catvie.domain.models.dto.auth.LoginDTO;
import me.dri.Catvie.domain.models.entities.User;
import me.dri.Catvie.domain.ports.interfaces.auth.AuthenticationPort;
import me.dri.Catvie.domain.ports.interfaces.auth.TokenServicesPort;
import me.dri.Catvie.infra.adapters.AuthenticationAdapter;
import me.dri.Catvie.infra.entities.UserEntity;
import me.dri.Catvie.infra.ports.EncoderPassword;
import me.dri.Catvie.infra.ports.MapperUserPort;
import me.dri.Catvie.infra.ports.UserRepositoryJPA;
import me.dri.Catvie.unittest.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Optional;


public class AuthServicesInfraTest {

    @Mock
    TokenServicesPort tokenServicesPort;
    @Mock
    UserRepositoryJPA repositoryJPA;
    @Mock
    MapperUserPort mapperUserPort;

    @Mock
    EncoderPassword encoderPassword;
    @Mock
    AuthenticationManager authenticationManager;

    AuthenticationPort authenticationPort;

    MockUser mockUser;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        authenticationPort = new AuthenticationAdapter(tokenServicesPort, repositoryJPA, mapperUserPort, encoderPassword, authenticationManager);
        mockUser = new MockUser();
    }

    @Test
    void registerTest() {
        User userRegister = this.mockUser.mockUser();
        UserEntity userEntity = this.mockUser.mockUserEntity();
        String passwordCrypted = "senhacriptografada";
        when(this.encoderPassword.encode(userEntity.getPassword())).thenReturn(passwordCrypted);
        when(this.mapperUserPort.convertUserToUserEntity(userRegister, passwordCrypted)).thenReturn(userEntity);
        this.authenticationPort.register(userRegister);
        verify(this.encoderPassword, times(1)).encode(userRegister.getPassword());
        verify(this.repositoryJPA, times(1)).save(userEntity);

    }

    @Test
    void loginTest() {
        UserEntity userEntity = this.mockUser.mockUserEntity();
        LoginDTO loginDTO = this.mockUser.mockLoginDTO();
        String tokenMock = "TokenTeste";
        when(this.repositoryJPA.findByEmail(loginDTO.email())).thenReturn(Optional.of(userEntity));
        when(this.tokenServicesPort.generateToken(any())).thenReturn(tokenMock);
        var tokenResponse = this.authenticationPort.login(loginDTO);
        verify(this.repositoryJPA, times(1)).findByEmail(any());
        verify(this.tokenServicesPort, times(1)).generateToken(any());
        verify(this.authenticationManager, times(1)).authenticate(any());
        assertEquals("TokenTeste", tokenResponse);

    }
    @Test
    void loginTestExceptionNotFoundUserByEmail() {
        LoginDTO loginDTO = this.mockUser.mockLoginDTO();
        when(this.repositoryJPA.findByEmail(loginDTO.email())).thenReturn(Optional.empty());
        var exception = assertThrows(InvalidEmailLogin.class, () -> this.authenticationPort.login(loginDTO));
        verify(this.tokenServicesPort, times(0)).generateToken(any());
        verify(this.authenticationManager, times(0)).authenticate(any());
        assertEquals("Not found user by email: " + loginDTO.email(), exception.getMessage());

    }

}
