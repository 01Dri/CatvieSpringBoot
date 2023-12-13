package me.dri.Catvie.domain.exceptions.validations;

import me.dri.Catvie.domain.exceptions.auth.NameRoleInvalid;
import me.dri.Catvie.domain.exceptions.validations.options.ValidationsOptions;
import me.dri.Catvie.domain.models.dto.auth.LoginDTO;
import me.dri.Catvie.domain.models.dto.auth.RegisterDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthValidations {
    private static final Logger logger = LoggerFactory.getLogger(AuthValidations.class);


    public static void validateLoginDTO(LoginDTO loginDTO) throws IllegalAccessException {
        ValidationsOptions.validateStringInputs(loginDTO);
        ValidationsOptions.validatePasswordLength(loginDTO);
    }

    public static void validateRegisterDTO(RegisterDTO registerDTO) throws IllegalAccessException {
        ValidationsOptions.validateStringInputs(registerDTO);
        ValidationsOptions.validatePasswordLength(registerDTO);
        validateRegisterDtoRoleName(registerDTO);
    }



     private static void validateRegisterDtoRoleName(RegisterDTO registerDTO) {

        try {
            if (!registerDTO.role().name().equals("USER") && !registerDTO.role().name().equals("ADMIN")) {
                logger.error("Input validation error in auth services");

                throw new NameRoleInvalid("Content 'role' " + "[" + registerDTO.role().name() + "] is invalid");
            }
        } catch (IllegalArgumentException e) {
            logger.error("Input validation error in auth services");
            throw new NameRoleInvalid("Content 'role' + [ " + registerDTO.role() + "] is invalid!");
        }
    }
}
