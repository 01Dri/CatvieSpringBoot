package me.dri.Catvie.domain.exceptions;

public class NotFoundFilm extends RuntimeException {

    public NotFoundFilm(String msg) {
        super(msg);
    }
}
