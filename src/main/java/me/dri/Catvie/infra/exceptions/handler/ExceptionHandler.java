package me.dri.Catvie.infra.exceptions.handler;

import jakarta.servlet.http.HttpServletRequest;
import me.dri.Catvie.domain.exceptions.ExceptionEntity;
import me.dri.Catvie.domain.exceptions.InvalidGenre;
import me.dri.Catvie.domain.exceptions.NotFoundDirector;
import me.dri.Catvie.domain.exceptions.auth.*;
import me.dri.Catvie.domain.exceptions.film.NotFoundFilm;
import me.dri.Catvie.domain.exceptions.token.ErrorGenerateTokenJWT;
import me.dri.Catvie.domain.exceptions.token.InvalidTokenJWT;
import me.dri.Catvie.domain.exceptions.user.AlreadyExistsUserException;
import me.dri.Catvie.domain.exceptions.user.NotFoundUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {


    private ThrowerExceptions throwerExceptions = new ThrowerExceptions();
    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundFilm.class)
    public ResponseEntity<ExceptionEntity> contentIsMissingException(NotFoundFilm e, HttpServletRequest request) {
        return this.throwerExceptions.throwException(e, request, HttpStatus.NOT_FOUND);
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidGenre.class)
    public ResponseEntity<ExceptionEntity> invalidGenre(InvalidGenre e, HttpServletRequest request) {
        return this.throwerExceptions.throwException(e, request, HttpStatus.NOT_FOUND);

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundDirector.class)
    public ResponseEntity<ExceptionEntity> notFoundDirector(NotFoundDirector e, HttpServletRequest request) {
        return this.throwerExceptions.throwException(e, request, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidEmailLogin.class)
    public ResponseEntity<ExceptionEntity> invalidInformation(InvalidEmailLogin e, HttpServletRequest request) {
        return this.throwerExceptions.throwException(e, request, HttpStatus.UNAUTHORIZED);

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidLoginPassword.class)
    public ResponseEntity<ExceptionEntity> invalidInformationPassword(InvalidLoginPassword e, HttpServletRequest request) {
        return this.throwerExceptions.throwException(e, request, HttpStatus.UNAUTHORIZED);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidJWTException.class)
    public ResponseEntity<ExceptionEntity> InvalidJWTException(InvalidJWTException e, HttpServletRequest request) {
        return this.throwerExceptions.throwException(e, request, HttpStatus.UNAUTHORIZED);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundUser.class)
    public ResponseEntity<ExceptionEntity> notFoundUser(NotFoundUser e, HttpServletRequest request) {
        return this.throwerExceptions.throwException(e, request, HttpStatus.NOT_FOUND);


    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MissingInformationInput.class)
    public ResponseEntity<ExceptionEntity> invalidInputsDto(MissingInformationInput e, HttpServletRequest request) {
        return this.throwerExceptions.throwException(e, request, HttpStatus.BAD_REQUEST);

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AlreadyExistsUserException.class)
    public ResponseEntity<ExceptionEntity> userAlreadyExist(AlreadyExistsUserException e, HttpServletRequest request) {
        return this.throwerExceptions.throwException(e, request, HttpStatus.CONFLICT);

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidCharacterInput.class)
    public ResponseEntity<ExceptionEntity> invalidCharacterInput(InvalidCharacterInput e, HttpServletRequest request) {
        return this.throwerExceptions.throwException(e, request, HttpStatus.CONFLICT);

    }


    @org.springframework.web.bind.annotation.ExceptionHandler(ErrorGenerateTokenJWT.class)
    public ResponseEntity<ExceptionEntity> errorGenerateJWToken(ErrorGenerateTokenJWT e, HttpServletRequest request) {
        return this.throwerExceptions.throwException(e, request, HttpStatus.UNAUTHORIZED);

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidTokenJWT.class)
    public ResponseEntity<ExceptionEntity> errorInvalidJWToken(InvalidTokenJWT e, HttpServletRequest request) {
        return this.throwerExceptions.throwException(e, request, HttpStatus.UNAUTHORIZED);

    }

}
