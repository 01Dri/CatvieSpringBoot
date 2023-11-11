package me.dri.Catvie.unittest.authtests;

import me.dri.Catvie.domain.models.entities.User;
import me.dri.Catvie.domain.ports.interfaces.AuthenticationPort;
import me.dri.Catvie.domain.ports.interfaces.TokenServicesPort;
import me.dri.Catvie.infra.adapters.AuthenticationAdapter;
import me.dri.Catvie.infra.adapters.EncoderPasswordAdapter;
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

import static org.mockito.Mockito.*;


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
}
