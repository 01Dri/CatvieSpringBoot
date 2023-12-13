package me.dri.Catvie.domain.exceptions.film;

public class NotFoundFilm extends RuntimeException {

    public NotFoundFilm(String msg) {
        super(msg);
    }
}
