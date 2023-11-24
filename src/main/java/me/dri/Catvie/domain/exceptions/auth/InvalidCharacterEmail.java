package me.dri.Catvie.domain.exceptions.auth;

public class InvalidCharacterEmail  extends  RuntimeException{

    public InvalidCharacterEmail(String msg) {
        super(msg);
    }
}
