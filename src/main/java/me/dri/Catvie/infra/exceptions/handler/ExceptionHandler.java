package me.dri.Catvie.infra.exceptions.handler;

import me.dri.Catvie.domain.exceptions.ContentIsMissing;
import me.dri.Catvie.domain.exceptions.ExceptionEntity;
import me.dri.Catvie.domain.exceptions.NotFoundEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ContentIsMissing.class)
    public ResponseEntity<ExceptionEntity> contentIsMissingException(ContentIsMissing e) {
        String error = "Content Is Missing";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value());
        return ResponseEntity.status(status).body(err);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundEntity.class)
    public ResponseEntity<ExceptionEntity> contentIsMissingException(NotFoundEntity e) {
        String error = "Not found entity";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value());
        return ResponseEntity.status(status).body(err);
    }
}
