package me.dri.Catvie.infra.exceptions.handler;

import jakarta.servlet.http.HttpServletRequest;
import me.dri.Catvie.domain.exceptions.ExceptionEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public class ThrowerExceptions {

    public  ResponseEntity<ExceptionEntity> throwException(RuntimeException e, HttpServletRequest request, HttpStatus status) {
        String ERROR_MESSAGE_REQUEST = "Failed request";
        String path = request.getRequestURI();
        ExceptionEntity exceptionEntity = new ExceptionEntity(
                new Date(),
                ERROR_MESSAGE_REQUEST,
                e.getMessage(),
                status.value(),
                path
        );
        return org.springframework.http.ResponseEntity.status(status).body(exceptionEntity);
    }

}

