package me.dri.Catvie.infra.exceptions.handler.film;


import jakarta.servlet.http.HttpServletRequest;
import me.dri.Catvie.domain.exceptions.ExceptionEntity;
import me.dri.Catvie.domain.exceptions.film.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;

@ControllerAdvice
public class FilmExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidAverageAudienceFilmException.class)
    public ResponseEntity<ExceptionEntity> averageValidationAudienceError(InvalidAverageAudienceFilmException e, HttpServletRequest request) {
        String error = "Average Audience validation error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value(), path);
        return ResponseEntity.status(status).body(err);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidAverageCriticFilmException.class)
    public ResponseEntity<ExceptionEntity> averageValidationCriticError(InvalidAverageCriticFilmException e, HttpServletRequest request) {
        String error = "Average critic validation error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value(), path);
        return ResponseEntity.status(status).body(err);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidDistributorFilmException.class)
    public ResponseEntity<ExceptionEntity> distributorValidationError(InvalidDistributorFilmException e, HttpServletRequest request) {
        String error = "Distributor validation error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value(), path);
        return ResponseEntity.status(status).body(err);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidLanguageFilmException.class)
    public ResponseEntity<ExceptionEntity> languageValidationError(InvalidLanguageFilmException e, HttpServletRequest request) {
        String error = "Original language validation error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value(), path);
        return ResponseEntity.status(status).body(err);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidProdutionFilmException.class)
    public ResponseEntity<ExceptionEntity> productionValidationError(InvalidProdutionFilmException e, HttpServletRequest request) {
        String error = "Production Co validation error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value(), path);
        return ResponseEntity.status(status).body(err);
    }

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

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidUrlImageFilmException.class)
    public ResponseEntity<ExceptionEntity> urlImageDateValidationError(InvalidUrlImageFilmException e, HttpServletRequest request) {
        String error = "Url image validation error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value(), path);
        return ResponseEntity.status(status).body(err);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidWriterFilmException.class)
    public ResponseEntity<ExceptionEntity> writerDateValidationError(InvalidWriterFilmException e, HttpServletRequest request) {
        String error = "Writer validation error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value(), path);
        return ResponseEntity.status(status).body(err);
    }
}
