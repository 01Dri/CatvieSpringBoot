package me.dri.Catvie.domain.exceptions.auth;

public class CharacterInvalidInPassword extends RuntimeException {

    public CharacterInvalidInPassword(String msg) {
        super(msg);
    }
}
