package me.dri.Catvie.domain.exceptions.user;

public class AlreadyExistsUserException extends RuntimeException {
    public AlreadyExistsUserException(String s) {
        super(s);
    }
}
