package me.dri.Catvie.domain.exceptions.validations.options;

import me.dri.Catvie.domain.exceptions.auth.InvalidCharacterInput;
import me.dri.Catvie.domain.exceptions.auth.MissingInformationInput;
import me.dri.Catvie.domain.exceptions.auth.PasswordLengthInvalid;

import java.lang.reflect.Field;

public class ValidationsOptions {


    public static void validateStringInputs(Object obj) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().isAssignableFrom(String.class)) {
                field.setAccessible(true);
                Object value = field.get(obj);
                try {
                    if (value.toString().isBlank() || value.toString().isEmpty()) {
                        throw new MissingInformationInput("Content " + field.getName() + " is empty");
                    }
                    if (value.toString().contains(" ")) {
                        throw new InvalidCharacterInput("Content " + field.getName() + " contains a invalid character");
                    }
                } catch (NullPointerException e) {
                    throw new MissingInformationInput("Content " + field.getName() + " is null");
                }
            }
        }
    }

    public static void validatePasswordLength(Object obj) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        Field fieldPassword = null;
        for (Field field : fields) {

            if (field.getName().equals("password")) {
                fieldPassword = field;
            }
        }
        if (fieldPassword != null) {
            fieldPassword.setAccessible(true);
            Object value = fieldPassword.get(obj);
            try {
                if (value.toString().length() < 8) {
                    throw new PasswordLengthInvalid("Password length invalid");
                }
            } catch (NullPointerException e) {
                throw new MissingInformationInput("Content " + fieldPassword.getName() + " is null");
            }
        }
    }

}
