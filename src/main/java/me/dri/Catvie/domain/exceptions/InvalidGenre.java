package me.dri.Catvie.domain.exceptions;

public class InvalidGenre  extends RuntimeException {

    public InvalidGenre(String msgError) {
        super(msgError);
    }
}
