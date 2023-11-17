package me.dri.Catvie.domain.exceptions.auth;

public class InvalidInformationLogin  extends RuntimeException {
    public InvalidInformationLogin(String msg) {
        super(msg);
    }
}
