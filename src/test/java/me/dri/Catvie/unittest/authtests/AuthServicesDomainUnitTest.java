package me.dri.Catvie.unittest.authtests;

import me.dri.Catvie.domain.adapters.services.auth.AuthenticationServiceImpl;
import me.dri.Catvie.domain.exceptions.auth.InvalidCharacterInput;
import me.dri.Catvie.domain.exceptions.auth.MissingInformationInput;
import me.dri.Catvie.domain.exceptions.auth.NameRoleInvalid;
import me.dri.Catvie.domain.exceptions.auth.PasswordLengthInvalid;
import me.dri.Catvie.domain.models.dto.auth.LoginDTO;
import me.dri.Catvie.domain.models.dto.auth.RegisterDTO;
import me.dri.Catvie.domain.models.core.User;
import me.dri.Catvie.domain.ports.interfaces.auth.AuthenticationPort;
import me.dri.Catvie.domain.ports.interfaces.mappers.MapperUserResponsePort;
import me.dri.Catvie.unittest.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
public class AuthServicesDomainUnitTest {


    @Mock
    AuthenticationPort authenticationPort;

    @Mock
    MapperUserResponsePort mapperUserPortDomain;

    @InjectMocks
    AuthenticationServiceImpl service;


    MockUser mockUser;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockUser = new MockUser();
    }

    @Test
    void registerTest() throws NoSuchFieldException, IllegalAccessException {
        RegisterDTO registerDTO = this.mockUser.mockRegisterDTO();
        User mockUser = this.mockUser.mockUser();
        when(this.mapperUserPortDomain.convertRegisterDTOToUser(registerDTO)).thenReturn(mockUser);
        this.service.register(registerDTO);
        verify(this.mapperUserPortDomain, times(1)).convertRegisterDTOToUser(registerDTO);
        verify(this.authenticationPort, times(1)).register(mockUser);
    }

    @Test
    void registerTestExceptionMissingInformationFirstNameEmpty() {
        RegisterDTO registerDTO = this.mockUser.mockRegisterDTOWithoutFirstNameEmpty();
        var exception = assertThrows(MissingInformationInput.class, () -> this.service.register(registerDTO));
        assertEquals("Content firstName is empty" , exception.getMessage());
        verify(this.mapperUserPortDomain, times(0)).convertRegisterDTOToUser(any());
        verify(this.authenticationPort, times(0)).register(any());

    }

    @Test
    void registerTestExceptionMissingInformationFirstNameNull() {
        RegisterDTO registerDTO = this.mockUser.mockRegisterDTOWithoutFirstNameNull();
        var exception = assertThrows(MissingInformationInput.class, () -> this.service.register(registerDTO));
        assertEquals("Content firstName is null", exception.getMessage());
        verify(this.mapperUserPortDomain, times(0)).convertRegisterDTOToUser(any());
        verify(this.authenticationPort, times(0)).register(any());
    }

    @Test
    void registerTestExceptionMissingInformationLastNameEmpty() {
        RegisterDTO registerDTO = this.mockUser.mockRegisterDTOWithoutLastNameEmpty();
        var exception = assertThrows(MissingInformationInput.class, () -> this.service.register(registerDTO));
        assertEquals("Content lastName is empty", exception.getMessage());
        verify(this.mapperUserPortDomain, times(0)).convertRegisterDTOToUser(any());
        verify(this.authenticationPort, times(0)).register(any());

    }


    @Test
    void registerTestExceptionMissingInformationLastNameNull() {
        RegisterDTO registerDTO = this.mockUser.mockRegisterDTOWithoutLastNameNull();
        var exception = assertThrows(MissingInformationInput.class, () -> this.service.register(registerDTO));
        assertEquals("Content lastName is null", exception.getMessage());
        verify(this.mapperUserPortDomain, times(0)).convertRegisterDTOToUser(any());
        verify(this.authenticationPort, times(0)).register(any());

    }



    @Test
    void registerTestExceptionMissingInformationPasswordEmpty() {
        RegisterDTO registerDTO = this.mockUser.mockRegisterDTOWithoutPasswordEmpty();
        var exception = assertThrows(MissingInformationInput.class, () -> this.service.register(registerDTO));
        assertEquals("Content password is empty", exception.getMessage());
        verify(this.mapperUserPortDomain, times(0)).convertRegisterDTOToUser(any());
        verify(this.authenticationPort, times(0)).register(any());

    }


    @Test
    void registerTestExceptionMissingInformationPasswordNull() {
        RegisterDTO registerDTO = this.mockUser.mockRegisterDTOWithoutPasswordNull();
        var exception = assertThrows(MissingInformationInput.class, () -> this.service.register(registerDTO));
        assertEquals("Content password is null", exception.getMessage());
        verify(this.mapperUserPortDomain, times(0)).convertRegisterDTOToUser(any());
        verify(this.authenticationPort, times(0)).register(any());

    }

    @Test
    void registerTestExceptionPasswordLengthInvalid() {
        RegisterDTO registerDTO = this.mockUser.mockRegisterDTOPasswordLengthInvalid();
        var exception = assertThrows(PasswordLengthInvalid.class, () -> this.service.register(registerDTO));
        assertEquals("Password length invalid", exception.getMessage());
        verify(this.mapperUserPortDomain, times(0)).convertRegisterDTOToUser(any());
        verify(this.authenticationPort, times(0)).register(any());
    }
    @Test
    void registerTestExceptionPasswordCharacterInvalid() {
        RegisterDTO registerDTO = this.mockUser.mockRegisterDTOCharacterInvalidPassword();
        var exception = assertThrows(InvalidCharacterInput.class, () -> this.service.register(registerDTO));
        assertEquals("Content have space between information", exception.getMessage());
        verify(this.mapperUserPortDomain, times(0)).convertRegisterDTOToUser(any());
        verify(this.authenticationPort, times(0)).register(any());
    }


    @Test
    void registerTestExceptionMissingInformationEmailEmpty() {
        RegisterDTO registerDTO = this.mockUser.mockRegisterDTOWithoutEmailEmpty();
        var exception = assertThrows(MissingInformationInput.class, () -> this.service.register(registerDTO));
        assertEquals("Content email is empty", exception.getMessage());
        verify(this.mapperUserPortDomain, times(0)).convertRegisterDTOToUser(any());
        verify(this.authenticationPort, times(0)).register(any());

    }
    @Test
    void registerTestExceptionInvalidCharacterEmail() {
        RegisterDTO registerDTO = this.mockUser.mockRegisterDTOWithoutInvalidEmail();
        var exception = assertThrows(InvalidCharacterInput.class, () -> this.service.register(registerDTO));
        assertEquals("Content have space between information", exception.getMessage());
        verify(this.mapperUserPortDomain, times(0)).convertRegisterDTOToUser(any());
        verify(this.authenticationPort, times(0)).register(any());

    }



    @Test
    void registerTestExceptionMissingInformationEmailNull() {
        RegisterDTO registerDTO = this.mockUser.mockRegisterDTOWithoutEmailNull();
        var exception = assertThrows(MissingInformationInput.class, () -> this.service.register(registerDTO));
        assertEquals("Content email is null", exception.getMessage());
        verify(this.mapperUserPortDomain, times(0)).convertRegisterDTOToUser(any());
        verify(this.authenticationPort, times(0)).register(any());

    }

    @Test
    void registerTestExceptionMissingInformationRoleEmpty() {
        RegisterDTO registerDTO = this.mockUser.mockRegisterDTOWithoutRoleEmpty();
       assertThrows(NameRoleInvalid.class, () -> this.service.register(registerDTO));
        verify(this.mapperUserPortDomain, times(0)).convertRegisterDTOToUser(any());
        verify(this.authenticationPort, times(0)).register(any());

    }


    @Test
    void registerTestExceptionMissingInformationRoleNull() {
        RegisterDTO registerDTO = this.mockUser.mockRegisterDTOWithoutRoleNull();
        assertThrows(NullPointerException.class, () -> this.service.register(registerDTO));
        verify(this.mapperUserPortDomain, times(0)).convertRegisterDTOToUser(any());
        verify(this.authenticationPort, times(0)).register(any());

    }

    @Test
    void registerTestExceptionInvalidRoleName() {
        RegisterDTO registerDTO = this.mockUser.mockRegisterDTOInvalidNameRole();
        assertThrows(NameRoleInvalid.class, () -> this.service.register(registerDTO));
        verify(this.mapperUserPortDomain, times(0)).convertRegisterDTOToUser(any());
        verify(this.authenticationPort, times(0)).register(any());

    }

    @Test
    void loginTest() throws NoSuchFieldException, IllegalAccessException {
        var loginDto = this.mockUser.mockLoginDTO();
        String token = "Tokenteste";
        when(this.authenticationPort.login(loginDto)).thenReturn(token);
        var tokenResponseDTO = this.service.login(loginDto);
        verify(this.authenticationPort, times(1)).login(loginDto);
        assertEquals(token, tokenResponseDTO.token());
    }


    @Test
    void loginTestExceptionMissingInformationEmailNull() {
        LoginDTO loginDTO = this.mockUser.mockLoginDTOWithoutEmailNull();
        var exception = assertThrows(MissingInformationInput.class, () -> this.service.login(loginDTO));
        assertEquals("Content email is null", exception.getMessage());
        verify(this.authenticationPort, times(0)).login(any());

    }

    @Test
    void loginTestExceptionMissingInformationEmailEmpty() {
        LoginDTO loginDTO = this.mockUser.mockLoginDTOWithoutEmailEmpty();
        var exception = assertThrows(MissingInformationInput.class, () -> this.service.login(loginDTO));
        assertEquals("Content email is empty", exception.getMessage());
        verify(this.authenticationPort, times(0)).login(any());

    }

    @Test
    void loginTestExceptionMissingInformationEmailInvalidCharacter() {
        LoginDTO loginDTO = this.mockUser.mockLoginDTOWithoutEmailInvalidCharacter();
        var exception = assertThrows(InvalidCharacterInput.class, () -> this.service.login(loginDTO));
        assertEquals("Content have space between information", exception.getMessage());
        verify(this.authenticationPort, times(0)).login(any());

    }

    @Test
    void loginTestExceptionMissingInformationPasswordEmpty() {
        LoginDTO loginDTO = this.mockUser.mockLoginDTOWithoutPasswordEmpty();
        var exception = assertThrows(MissingInformationInput.class, () -> this.service.login(loginDTO));
        assertEquals("Content password is empty", exception.getMessage());
        verify(this.authenticationPort, times(0)).login(any());

    }

    @Test
    void loginTestExceptionMissingInformationPasswordNull() {
        LoginDTO loginDTO = this.mockUser.mockLoginDTOWithoutPasswordNull();
        var exception = assertThrows(MissingInformationInput.class, () -> this.service.login(loginDTO));
        assertEquals("Content password is null", exception.getMessage());
        verify(this.authenticationPort, times(0)).login(any());

    }

    @Test
    void loginTestExceptionMissingInformationInvalidCharacterPassword() {
        LoginDTO loginDTO = this.mockUser.mockLoginDTOWithoutPasswordInvalidCharacter();
        var exception = assertThrows(InvalidCharacterInput.class, () -> this.service.login(loginDTO));
        assertEquals("Content have space between information", exception.getMessage());
        verify(this.authenticationPort, times(0)).login(any());

    }





}
