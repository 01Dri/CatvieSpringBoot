package me.dri.Catvie.infra.exceptions.handler.notes;

import jakarta.servlet.http.HttpServletRequest;
import me.dri.Catvie.domain.exceptions.ExceptionEntity;
import me.dri.Catvie.domain.exceptions.notes.InvalidIdException;
import me.dri.Catvie.domain.exceptions.notes.InvalidNoteException;
import me.dri.Catvie.domain.exceptions.notes.UserAlreadyRatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;

@ControllerAdvice
public class NotesHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidNoteException.class)
    public ResponseEntity<ExceptionEntity> invalidNoteNumber(InvalidNoteException e, HttpServletRequest request) {
        String error = "Invalid Note number";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value(), path);
        return ResponseEntity.status(status).body(err);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidIdException.class)
    public ResponseEntity<ExceptionEntity> invalidIdNumber(InvalidIdException e, HttpServletRequest request) {
        String error = "Invalid id number";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value(), path);
        return ResponseEntity.status(status).body(err);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(UserAlreadyRatedException.class)
    public ResponseEntity<ExceptionEntity> userAlreadyRated(UserAlreadyRatedException e, HttpServletRequest request) {
        String error = "User already in database";
        HttpStatus status = HttpStatus.CONFLICT;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value(), path);
        return ResponseEntity.status(status).body(err);
    }




}
