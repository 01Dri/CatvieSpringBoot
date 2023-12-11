package me.dri.Catvie.unittest.authtests;

import me.dri.Catvie.domain.exceptions.auth.InvalidEmailLogin;
import me.dri.Catvie.domain.models.dto.auth.LoginDTO;
import me.dri.Catvie.domain.models.entities.User;
import me.dri.Catvie.domain.ports.interfaces.auth.TokenServicesPort;
import me.dri.Catvie.infra.adapters.AuthenticationAdapter;
import me.dri.Catvie.infra.entities.UserEntity;
import me.dri.Catvie.infra.jpa.UserRepositoryJPA;
import me.dri.Catvie.infra.ports.auth.EncoderPassword;
import me.dri.Catvie.unittest.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class AuthServicesInfraUnitTest {

    @Mock
    TokenServicesPort tokenServicesPort;
    @Mock
    UserRepositoryJPA repositoryJPA;
    @Mock
    EncoderPassword encoderPassword;
    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    AuthenticationAdapter authentication;

    MockUser mockUser;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockUser = new MockUser();
    }

    @Test
    void registerTest() {
        User userRegister = this.mockUser.mockUser();
        UserEntity userEntity = this.mockUser.mockUserEntity();
        String passwordCrypted = "senhacriptografada";
        when(this.encoderPassword.encode(userEntity.getPassword())).thenReturn(passwordCrypted);
        when(this.modelMapper.map(userEntity, User.class)).thenReturn(userRegister);
        this.authentication.register(userRegister);
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
        var tokenResponse = this.authentication.login(loginDTO);
        verify(this.repositoryJPA, times(1)).findByEmail(any());
        verify(this.tokenServicesPort, times(1)).generateToken(any());
        verify(this.authenticationManager, times(1)).authenticate(any());
        assertEquals("TokenTeste", tokenResponse);

    }
    @Test
    void loginTestExceptionNotFoundUserByEmail() {
        LoginDTO loginDTO = this.mockUser.mockLoginDTO();
        when(this.repositoryJPA.findByEmail(loginDTO.email())).thenReturn(Optional.empty());
        var exception = assertThrows(InvalidEmailLogin.class, () -> this.authentication.login(loginDTO));
        verify(this.tokenServicesPort, times(0)).generateToken(any());
        verify(this.authenticationManager, times(0)).authenticate(any());
        assertEquals("Not found user by email: " + loginDTO.email(), exception.getMessage());

    }

}
