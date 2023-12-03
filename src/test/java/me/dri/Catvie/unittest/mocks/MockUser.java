package me.dri.Catvie.unittest.mocks;


import me.dri.Catvie.domain.enums.UserRole;
import me.dri.Catvie.domain.models.dto.auth.LoginDTO;
import me.dri.Catvie.domain.models.dto.auth.RegisterDTO;
import me.dri.Catvie.domain.models.dto.auth.RegisterResponseDTO;
import me.dri.Catvie.domain.models.dto.auth.TokenResponseDTO;
import me.dri.Catvie.domain.models.dto.user.UserDTO;
import me.dri.Catvie.domain.models.dto.user.UserResponseDTO;
import me.dri.Catvie.domain.models.entities.User;
import me.dri.Catvie.infra.entities.UserEntity;

public class MockUser {

    public MockUser() {

    }

    public User mockUser() {
        return new User(1L, "diego", "henrique", "diego@gmail.com", "12345678", "tokentest", UserRole.ADMIN);
    }

    public UserDTO mockUserDTO() {
        return new UserDTO("diego", "henrique", "diego@gmail", "12345678", "tokentest", UserRole.ADMIN);
    }

    public RegisterDTO mockRegisterDTO() {
        return new RegisterDTO("diego", "henrique", "diego@gmail.com", "testesenha", UserRole.ADMIN);
    }


    public RegisterResponseDTO mockRegisterResponseDTO(String firstName, String lastName, String email) {
        return new RegisterResponseDTO(firstName, lastName, email);

    }

    public UserEntity mockUserEntity() {
        return new UserEntity(1L, "DIEGO", "HENRIQUE", "DIEGO@GMAIL", "12345678", "TOKENTEST", UserRole.ADMIN);
    }

    public LoginDTO mockLoginDTO() {
        return new LoginDTO("diego@gmail.com", "testesenha");
    }

    public TokenResponseDTO mockResponseToken() {
        return new TokenResponseDTO("tokenteste");
    }

    public RegisterDTO mockRegisterDTOWithoutFirstNameEmpty() {
        return new RegisterDTO(" ", "henrique", "diego@gmail", "testesenha",  UserRole.ADMIN);
    }

    public RegisterDTO mockRegisterDTOWithoutFirstNameNull() {
        return new RegisterDTO(null, "henrique", "diego@gmail", "testesenha",  UserRole.ADMIN);
    }

    public RegisterDTO mockRegisterDTOWithoutLastNameEmpty() {
        return new RegisterDTO("Diego", "", "diego@gmail", "testesenha",  UserRole.ADMIN);
    }

    public RegisterDTO mockRegisterDTOWithoutLastNameNull() {
        return new RegisterDTO("Diego", null, "diego@gmail", "testesenha",  UserRole.ADMIN);
    }

    public RegisterDTO mockRegisterDTOWithoutPasswordEmpty() {
        return new RegisterDTO("Diego", "Henroque", "diego@gmail", "",  UserRole.ADMIN);
    }

    public RegisterDTO mockRegisterDTOWithoutPasswordNull() {
        return new RegisterDTO("Diego", "Henrique", "diego@gmail", null,  UserRole.ADMIN);
    }

    public RegisterDTO mockRegisterDTOCharacterInvalidPassword() {
        return new RegisterDTO("Diego", "Henrique", "diego@gmail.com", "teste senha",  null);
    }


    public RegisterDTO mockRegisterDTOWithoutEmailEmpty() {
        return new RegisterDTO("Diego", "Henroque", "", "testesenha",  UserRole.ADMIN);
    }

    public RegisterDTO mockRegisterDTOWithoutEmailNull() {
        return new RegisterDTO("Diego", "Henrique", null, "testesenha",  UserRole.ADMIN);
    }
    public RegisterDTO mockRegisterDTOWithoutInvalidEmail() {
        return new RegisterDTO("Diego", "Henrique", "diego @gmail.com", "12345678",  UserRole.USER);

    }

    public RegisterDTO mockRegisterDTOWithoutRoleEmpty() {
        return new RegisterDTO("Diego", "Henroque", "diego@gmail.com", "testesenha",  UserRole.EMPTY);
    }

    public RegisterDTO mockRegisterDTOWithoutRoleNull() {
        return new RegisterDTO("Diego", "Henrique", "diego@gmail.com", "testesenha",  null);
    }


    public RegisterDTO mockRegisterDTOPasswordLengthInvalid() {
        return new RegisterDTO("Diego", "Henrique", "diego@gmail.com", "1234567",  UserRole.USER);
    }

    public RegisterDTO mockRegisterDTOInvalidNameRole() {
        return new RegisterDTO("Diego", "Henrique", "diego@gmail.com", "12345678",  UserRole.EMPTY);

    }


    public UserResponseDTO mockUserResponseDTO() {
        return new UserResponseDTO("diego@gmail.com");
    }

    public LoginDTO mockLoginDTOWithoutEmailEmpty() {
        return new LoginDTO("", "12345678");
    }

    public LoginDTO mockLoginDTOWithoutEmailNull() {
        return new LoginDTO(null, "12345678");
    }

    public LoginDTO mockLoginDTOWithoutPasswordEmpty() {
        return new LoginDTO("diego@gmail.com", "");
    }
    public LoginDTO mockLoginDTOWithoutPasswordNull() {
        return new LoginDTO("diego@gmail.com", null);
    }

    public LoginDTO mockLoginDTOWithoutEmailInvalidCharacter() {
        return new LoginDTO("diego @gmail.com", "12345678");
    }

    public LoginDTO mockLoginDTOWithoutPasswordInvalidCharacter() {
        return new LoginDTO("diego@gmail.com", "1234 5678");
    }

}
