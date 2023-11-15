package me.dri.Catvie.infra.exceptions.handler;

import me.dri.Catvie.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ContentInformationsFilmMissing.class)
    public ResponseEntity<ExceptionEntity> contentIsMissingException(ContentInformationsFilmMissing e) {
        String error = "Content Is Missing";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value());
        return ResponseEntity.status(status).body(err);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundFilm.class)
    public ResponseEntity<ExceptionEntity> contentIsMissingException(NotFoundFilm e) {
        String error = "Not found entity";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value());
        return ResponseEntity.status(status).body(err);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidGenre.class)
    public ResponseEntity<ExceptionEntity> invalidGenre(InvalidGenre e) {
        String error = "Genre not found";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value());
        return ResponseEntity.status(status).body(err);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundDirector.class)
    public ResponseEntity<ExceptionEntity> notFoundDirector(NotFoundDirector e) {
        String error = "Director not found";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value());
        return ResponseEntity.status(status).body(err);
    }
}
