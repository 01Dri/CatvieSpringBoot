package me.dri.Catvie.domain.exceptions.validations.options;

import me.dri.Catvie.domain.exceptions.auth.InvalidCharacterInput;
import me.dri.Catvie.domain.exceptions.auth.MissingInformationInput;
import me.dri.Catvie.domain.exceptions.auth.PasswordLengthInvalid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class ValidationsOptions {

    private static final Logger logger = LoggerFactory.getLogger(ValidationsOptions.class);


    // The objective for this class is to validate each string input in the object DTO in generic way

    public static void validateStringInputs(Object obj) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields(); // Getting all attributes of the obj
        for (Field field : fields) {
            if (field.getType().isAssignableFrom(String.class)) { // Verifying if the attribute is a String
                field.setAccessible(true); // Allowing the access to the value of attribute
                Object value = field.get(obj);

                // Validations
                try {
                    if (value.toString().isBlank() || value.toString().isEmpty()) {
                        logger.error("Input validation error in auth services");
                        throw new MissingInformationInput("Content " + field.getName() + " is empty");
                    }

                } catch (NullPointerException e) {
                    logger.error("Input validation error in auth services");
                    throw new MissingInformationInput("Content " + field.getName() + " is null");
                }
            }
        }
    }

    public static void validatePasswordLength(Object obj) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields(); // Getting all attributes of obj
        Field fieldPassword = null;
        for (Field field : fields) {

            if (field.getName().equals("password")) { // Verifying if attribute have the name "password"
                fieldPassword = field;
            }
        }
        if (fieldPassword != null) {
            fieldPassword.setAccessible(true);
            Object value = fieldPassword.get(obj);
            try {
                if (value.toString().length() < 8) { // Verifying the length of password

                    logger.error("Input validation error in auth services");
                    throw new PasswordLengthInvalid("Password length invalid");
                }

                if (value.toString().contains(" ") ) {
                    logger.error("Input validation error in auth services");
                    throw new InvalidCharacterInput("Content " + fieldPassword.getName() + " contains a invalid character");
                }
            } catch (NullPointerException e) {
                logger.error("Input validation error in auth services");
                throw new MissingInformationInput("Content " + fieldPassword.getName() + " is null");
            }
        }
    }

}
