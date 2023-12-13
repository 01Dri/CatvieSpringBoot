package me.dri.Catvie.domain.exceptions.auth;

public class PasswordLengthInvalid extends RuntimeException {

    public PasswordLengthInvalid(String msg) {
        super(msg);
    }
}
