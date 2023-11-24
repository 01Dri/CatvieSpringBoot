package me.dri.Catvie.infra.exceptions.handler.auth;

import jakarta.servlet.http.HttpServletRequest;
import me.dri.Catvie.domain.exceptions.ExceptionEntity;
import me.dri.Catvie.domain.exceptions.auth.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;

@ControllerAdvice
public class HandlerExceptionAuth {

    @org.springframework.web.bind.annotation.ExceptionHandler(MissingInformationEmail.class)
    public ResponseEntity<ExceptionEntity> contentIsMissingEmailInformation(MissingInformationEmail e, HttpServletRequest request) {
        String error = "Email validation error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value(), path);
        return ResponseEntity.status(status).body(err);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MissingInformationFirstName.class)
    public ResponseEntity<ExceptionEntity> contentIsMissingFirstNameInformation(MissingInformationFirstName e, HttpServletRequest request) {
        String error = "FirstName validation error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value() ,path);
        return ResponseEntity.status(status).body(err);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MissingInformationLastName.class)
    public ResponseEntity<ExceptionEntity> contentIsMissingLastNameInformation(MissingInformationLastName e, HttpServletRequest request) {
        String error = "LastName validation error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value(), path);
        return ResponseEntity.status(status).body(err);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MissingInformationPassword.class)
    public ResponseEntity<ExceptionEntity> contentIsMissingPasswordInformation(MissingInformationPassword e, HttpServletRequest request) {
        String error = "Password validation error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value(), path);
        return ResponseEntity.status(status).body(err);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MissingInformationRole.class)
    public ResponseEntity<ExceptionEntity> contentIsMissingRoleInformation(MissingInformationRole e, HttpServletRequest request) {
        String error = "Role validation error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value(), path);
        return ResponseEntity.status(status).body(err);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NameRoleInvalid.class)
    public ResponseEntity<ExceptionEntity> contentIsMissingNameRoleInformation(NameRoleInvalid e, HttpServletRequest request) {
        String error = "Role validation error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value(), path);
        return ResponseEntity.status(status).body(err);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(PasswordLengthInvalid.class)
    public ResponseEntity<ExceptionEntity> contentIsMissingPasswordLengthInformation(PasswordLengthInvalid e, HttpServletRequest request) {
        String error = "Password validation error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value(), path);
        return ResponseEntity.status(status).body(err);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(CharacterInvalidInPassword.class)
    public ResponseEntity<ExceptionEntity> contentIsMissingPasswordCharacterInformation(CharacterInvalidInPassword e, HttpServletRequest request) {
        String error = "Password validation error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value(), path);
        return ResponseEntity.status(status).body(err);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidCharacterEmail.class)
    public ResponseEntity<ExceptionEntity> contentIsMissingEmailCharacterInformation(InvalidCharacterEmail e, HttpServletRequest request) {
        String error = "Email validation error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value(), path);
        return ResponseEntity.status(status).body(err);
    }


}
