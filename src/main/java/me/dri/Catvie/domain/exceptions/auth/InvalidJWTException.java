package me.dri.Catvie.domain.exceptions.auth;

public class InvalidJWTException extends RuntimeException {

    public InvalidJWTException(String msg) {
        super(msg);
    }
}
