package me.dri.Catvie.infra.exceptions.handler.film;


import jakarta.servlet.http.HttpServletRequest;
import me.dri.Catvie.domain.exceptions.ExceptionEntity;
import me.dri.Catvie.domain.exceptions.film.IdFilmIsNullException;
import me.dri.Catvie.domain.exceptions.film.InvalidReleaseDateFilmException;
import me.dri.Catvie.domain.exceptions.film.InvalidRuntimeFilmException;
import me.dri.Catvie.domain.exceptions.film.InvalidTitleFilmException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;

@ControllerAdvice
public class FilmExceptionHandler {



    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidReleaseDateFilmException.class)
    public ResponseEntity<ExceptionEntity> releaseDateValidationError(InvalidReleaseDateFilmException e, HttpServletRequest request) {
        String error = "Release Date validation error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value(), path);
        return ResponseEntity.status(status).body(err);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidRuntimeFilmException.class)
    public ResponseEntity<ExceptionEntity> releaseDateValidationError(InvalidRuntimeFilmException e, HttpServletRequest request) {
        String error = "Runtime validation error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value(), path);
        return ResponseEntity.status(status).body(err);
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidTitleFilmException.class)
    public ResponseEntity<ExceptionEntity> titleDateValidationError(InvalidTitleFilmException e, HttpServletRequest request) {
        String error = "Title validation error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value(), path);
        return ResponseEntity.status(status).body(err);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(IdFilmIsNullException.class)
    public ResponseEntity<ExceptionEntity> idForFilmIsNull(IdFilmIsNullException e, HttpServletRequest request) {
        String error = "Id Film validation error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value(), path);
        return ResponseEntity.status(status).body(err);
    }
}
