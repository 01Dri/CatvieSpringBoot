package me.dri.Catvie.domain.exceptions.notes;

public class UserAlreadyRatedException extends RuntimeException {
    public UserAlreadyRatedException(String s) {
        super(s);
    }
}
