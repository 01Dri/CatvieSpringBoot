package me.dri.Catvie.domain.exceptions.token;

public class InvalidTokenJWT  extends RuntimeException {
    public InvalidTokenJWT(String msg) {
        super(msg);
    }
}
