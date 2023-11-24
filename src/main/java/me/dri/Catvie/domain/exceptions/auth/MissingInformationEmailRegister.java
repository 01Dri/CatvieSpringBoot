package me.dri.Catvie.domain.exceptions.auth;

public class MissingInformationEmailRegister extends RuntimeException {

    public MissingInformationEmailRegister(String msg) {
        super(msg);
    }
}
