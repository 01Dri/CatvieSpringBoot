package me.dri.Catvie.domain.exceptions.user;

public class NotFoundUser extends RuntimeException {
    public NotFoundUser(String msg) {
        super(msg);
    }
}
