package me.dri.Catvie.unittest.validationstests;

import me.dri.Catvie.domain.exceptions.auth.MissingInformationInput;
import me.dri.Catvie.domain.exceptions.auth.PasswordLengthInvalid;
import me.dri.Catvie.domain.exceptions.validations.AuthValidations;
import me.dri.Catvie.domain.models.dto.auth.LoginDTO;
import me.dri.Catvie.unittest.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
public class ValidationTests {

    MockUser mockUser;

    @BeforeEach
    void setup() {
        mockUser = new MockUser();

    }

    @Test
    void testValidationsEmpty() {
        LoginDTO loginDTO = this.mockUser.mockLoginDTOWithoutEmailEmpty();
        assertThrows(MissingInformationInput.class, () -> AuthValidations.validateLoginDTO(loginDTO));

    }

    @Test
    void testValidationsNull() {
        LoginDTO loginDTO = this.mockUser.mockLoginDTOWithoutPasswordNull();
        assertThrows(MissingInformationInput.class, () -> AuthValidations.validateLoginDTO(loginDTO));

    }

    @Test
    void testValidationsLength() {
        LoginDTO loginDTO = this.mockUser.mockLoginDTOLengthPass();
        assertThrows(PasswordLengthInvalid.class, () -> AuthValidations.validateLoginDTO(loginDTO));

    }


}
