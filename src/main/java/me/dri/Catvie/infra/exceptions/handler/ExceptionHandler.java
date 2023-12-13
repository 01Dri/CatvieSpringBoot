package me.dri.Catvie.infra.exceptions.handler;

import jakarta.servlet.http.HttpServletRequest;
import me.dri.Catvie.domain.exceptions.ExceptionEntity;
import me.dri.Catvie.domain.exceptions.InvalidGenre;
import me.dri.Catvie.domain.exceptions.NotFoundDirector;
import me.dri.Catvie.domain.exceptions.auth.*;
import me.dri.Catvie.domain.exceptions.film.NotFoundFilm;
import me.dri.Catvie.domain.exceptions.user.AlreadyExistsUserException;
import me.dri.Catvie.domain.exceptions.user.NotFoundUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandler {


    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundFilm.class)
    public ResponseEntity<ExceptionEntity> contentIsMissingException(NotFoundFilm e, HttpServletRequest request) {
        String error = "Not found entity";
        HttpStatus status = HttpStatus.NOT_FOUND;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value(), path);
        return ResponseEntity.status(status).body(err);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidGenre.class)
    public ResponseEntity<ExceptionEntity> invalidGenre(InvalidGenre e, HttpServletRequest request) {
        String error = "Genre not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value(), path);
        return ResponseEntity.status(status).body(err);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundDirector.class)
    public ResponseEntity<ExceptionEntity> notFoundDirector(NotFoundDirector e, HttpServletRequest request) {
        String error = "Director not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value(), path);
        return ResponseEntity.status(status).body(err);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidEmailLogin.class)
    public ResponseEntity<ExceptionEntity> invalidInformation(InvalidEmailLogin e, HttpServletRequest request) {
        String error = "Email invalid!!!";
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value(), path);
        return ResponseEntity.status(status).body(err);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidLoginPassword.class)
    public ResponseEntity<ExceptionEntity> invalidInformationPassword(InvalidLoginPassword e, HttpServletRequest request) {
        String error = "Password invalid!!!";
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value(), path);
        return ResponseEntity.status(status).body(err);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidJWTException.class)
    public ResponseEntity<ExceptionEntity> InvalidJWTException(InvalidJWTException e, HttpServletRequest request) {
        String error = "Token invalid!!!!";
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value(), path);
        return ResponseEntity.status(status).body(err);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundUser.class)
    public ResponseEntity<ExceptionEntity> notFoundUser(NotFoundUser e, HttpServletRequest request) {
        String error = "Not found user";
        HttpStatus status = HttpStatus.NOT_FOUND;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value(), path);
        return ResponseEntity.status(status).body(err);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MissingInformationInput.class)
    public ResponseEntity<ExceptionEntity> invalidInputsDto(MissingInformationInput e, HttpServletRequest request) {
        String error = "Error invalid input DTO";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value(), path);
        return ResponseEntity.status(status).body(err);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AlreadyExistsUserException.class)
    public ResponseEntity<ExceptionEntity> userAlreadyExist(AlreadyExistsUserException e, HttpServletRequest request) {
        String error = "Error registration the user";
        HttpStatus status = HttpStatus.CONFLICT;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value(), path);
        return ResponseEntity.status(status).body(err);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidCharacterInput.class)
    public ResponseEntity<ExceptionEntity> invalidCharacterInput(InvalidCharacterInput e, HttpServletRequest request) {
        String error = "Error invalid input DTO";
        HttpStatus status = HttpStatus.CONFLICT;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value(), path);
        return ResponseEntity.status(status).body(err);
    }



}

