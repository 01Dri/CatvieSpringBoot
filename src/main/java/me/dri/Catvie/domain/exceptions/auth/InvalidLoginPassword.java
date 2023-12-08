package me.dri.Catvie.domain.exceptions.auth;

public class InvalidLoginPassword extends RuntimeException {
    public InvalidLoginPassword(String msg) {
        super(msg);
    }
}
