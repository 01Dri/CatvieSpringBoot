package me.dri.Catvie.domain.exceptions.auth;

public class MissingInformationEmail extends RuntimeException {

    public MissingInformationEmail(String msg) {
        super(msg);
    }
}
