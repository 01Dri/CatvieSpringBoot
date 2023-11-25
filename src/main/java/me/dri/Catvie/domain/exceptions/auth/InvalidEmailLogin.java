package me.dri.Catvie.domain.exceptions.auth;

public class InvalidEmailLogin extends RuntimeException {
    public InvalidEmailLogin(String msg) {
        super(msg);
    }
}
