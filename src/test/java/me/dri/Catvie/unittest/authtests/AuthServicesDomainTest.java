package me.dri.Catvie.unittest.authtests;

import me.dri.Catvie.domain.adapters.services.AuthenticationServiceImpl;
import me.dri.Catvie.domain.models.dto.RegisterDTO;
import me.dri.Catvie.domain.models.entities.User;
import me.dri.Catvie.domain.ports.interfaces.AuthenticationPort;
import me.dri.Catvie.domain.ports.interfaces.AuthenticationServicePort;
import me.dri.Catvie.domain.ports.interfaces.MapperUserPort;
import me.dri.Catvie.domain.ports.repositories.UserRepositoryPort;
import me.dri.Catvie.unittest.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
public class AuthServicesDomainTest {


    AuthenticationServicePort service;

    @Mock
    AuthenticationPort authenticationPort;

    @Mock
    MapperUserPort mapperUserPortDomain;

    @Mock
    UserRepositoryPort userRepositoryPort;


    MockUser mockUser;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        service = new AuthenticationServiceImpl(authenticationPort, mapperUserPortDomain, userRepositoryPort);
        mockUser = new MockUser();
    }

    @Test
    void registerTest() {
        RegisterDTO registerDTO = this.mockUser.mockRegisterDTO();
        User mockUser = this.mockUser.mockUser();
        when(this.mapperUserPortDomain.convertRegisterDTOToUser(registerDTO)).thenReturn(mockUser);
        this.service.register(registerDTO);
        verify(this.mapperUserPortDomain, times(1)).convertRegisterDTOToUser(registerDTO);
        verify(this.authenticationPort, times(1)).register(mockUser);
    }

    @Test
    void loginTest() {
        var loginDto = this.mockUser.mockLoginDTO();
        var user = this.mockUser.mockUser();
        var tokenResponseDTO = this.mockUser.mockResponseToken();
        when(this.mapperUserPortDomain.convertLoginDTOToUser(loginDto)).thenReturn(user);
        this.service.login(loginDto);
        verify(this.authenticationPort, times(1)).login(user);
    }



}
