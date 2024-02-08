package me.dri.Catvie.infra.exceptions.handler.film;


import jakarta.servlet.http.HttpServletRequest;
import me.dri.Catvie.domain.exceptions.ExceptionEntity;
import me.dri.Catvie.domain.exceptions.film.IdFilmIsNullException;
import me.dri.Catvie.domain.exceptions.film.InvalidReleaseDateFilmException;
import me.dri.Catvie.domain.exceptions.film.InvalidRuntimeFilmException;
import me.dri.Catvie.domain.exceptions.film.InvalidTitleFilmException;
import me.dri.Catvie.infra.exceptions.handler.ThrowerExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class FilmExceptionHandler {

    private ThrowerExceptions throwerExceptions = new ThrowerExceptions();

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidReleaseDateFilmException.class)
    public ResponseEntity<ExceptionEntity> releaseDateValidationError(InvalidReleaseDateFilmException e, HttpServletRequest request) {
        return this.throwerExceptions.throwException(e, request, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidRuntimeFilmException.class)
    public ResponseEntity<ExceptionEntity> releaseDateValidationError(InvalidRuntimeFilmException e, HttpServletRequest request) {
        return this.throwerExceptions.throwException(e, request, HttpStatus.BAD_REQUEST);

    }


    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidTitleFilmException.class)
    public ResponseEntity<ExceptionEntity> titleDateValidationError(InvalidTitleFilmException e, HttpServletRequest request) {
        return this.throwerExceptions.throwException(e, request, HttpStatus.BAD_REQUEST);

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(IdFilmIsNullException.class)
    public ResponseEntity<ExceptionEntity> idForFilmIsNull(IdFilmIsNullException e, HttpServletRequest request) {
        return this.throwerExceptions.throwException(e, request, HttpStatus.BAD_REQUEST);
    }
}
