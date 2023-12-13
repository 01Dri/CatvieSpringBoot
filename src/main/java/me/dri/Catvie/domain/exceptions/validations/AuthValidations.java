package me.dri.Catvie.domain.exceptions.validations;

import me.dri.Catvie.domain.exceptions.auth.*;
import me.dri.Catvie.domain.models.dto.auth.LoginDTO;
import me.dri.Catvie.domain.models.dto.auth.RegisterDTO;

import java.lang.reflect.Field;

public class UserValidations {

    public static void validateLoginDTO(LoginDTO loginDTO) throws NoSuchFieldException {
        validateDtosStringsInputs(loginDTO.email(), getNameInput(loginDTO, "email"));
        validateDtosStringsInputs(loginDTO.password(), getNameInput(loginDTO, "password"));
        validatePasswordLength(loginDTO.password(), getNameInput(loginDTO, "password"));
    }

    public static void validateRegisterDTO(RegisterDTO registerDTO) throws NoSuchFieldException {
        validateDtosStringsInputs(registerDTO.firstName(), getNameInput(registerDTO, "firstName"));
        validateDtosStringsInputs(registerDTO.lastName(), getNameInput(registerDTO, "lastName"));
        validateDtosStringsInputs(registerDTO.email(), getNameInput(registerDTO, "email"));
        validateDtosStringsInputs(registerDTO.password(), getNameInput(registerDTO, "password"));
        validateDtosStringsInputs(registerDTO.role().name(), getNameInput(registerDTO, "role"));
        validatePasswordLength(registerDTO.password(), getNameInput(registerDTO, "password"));
        validateRegisterDtoRoleName(registerDTO);
    }


    private static void validateDtosStringsInputs(String input, String nameInput) {
        try {
            if (input.isBlank() || input.isEmpty()) {
                throw new MissingInformationInput("Content " + nameInput + " is empty");
            }
            if (input.contains(" ")) {
                throw new InvalidCharacterInput("Content " + nameInput + " contains a invalid character");
            }
        } catch (NullPointerException e) {
            throw new MissingInformationInput("Content " + nameInput + " is null");
        }
    }

    private static String getNameInput(Object loginDTO, String field) throws NoSuchFieldException {
        Field fields = loginDTO.getClass().getDeclaredField(field);
        return fields.getName();
    }


     private static void validateRegisterDtoRoleName(RegisterDTO registerDTO) {

        try {
            if (!registerDTO.role().name().equals("USER") && !registerDTO.role().name().equals("ADMIN")) {
                throw new NameRoleInvalid("Content 'role' " + "[" + registerDTO.role().name() + "] is invalid");
            }
        } catch (IllegalArgumentException e) {
            throw new NameRoleInvalid("Content 'role' + [ " + registerDTO.role() + "] is invalid!");
        }
    }

    private static void validatePasswordLength(String password, String nameInput) {
        try {
            if (password.length() < 8) {
                throw new PasswordLengthInvalid("Password length invalid");
            }
        } catch (NullPointerException e) {
            throw new MissingInformationInput("Content " + nameInput + " is null");
        }
    }

}
