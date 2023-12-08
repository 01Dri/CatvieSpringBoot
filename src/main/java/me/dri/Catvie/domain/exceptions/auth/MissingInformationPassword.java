package me.dri.Catvie.domain.exceptions.auth;

public class MissingInformationPassword extends RuntimeException {

    public MissingInformationPassword(String msg) {
        super(msg);
    }
}
