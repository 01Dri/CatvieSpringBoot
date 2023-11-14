package me.dri.Catvie.unittest.mocks;


import me.dri.Catvie.domain.enums.UserRole;
import me.dri.Catvie.domain.models.dto.*;
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
        return new RegisterDTO("diego", "henrique", "diego@gmail", "testesenha", "tokentest", UserRole.ADMIN);
    }


    public RegisterResponseDTO mockRegisterResponseDTO(String firstName, String lastName, String email, String token) {
        return new RegisterResponseDTO(firstName, lastName, email, token);

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


}
