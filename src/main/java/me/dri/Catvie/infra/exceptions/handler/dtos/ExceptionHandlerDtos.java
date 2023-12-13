package me.dri.Catvie.infra.exceptions.handler.dtos;

import jakarta.servlet.http.HttpServletRequest;
import me.dri.Catvie.domain.exceptions.ExceptionEntity;
import me.dri.Catvie.domain.exceptions.auth.MissingInformationInput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;
@ControllerAdvice
public class ExceptionHandlerDtos {

    @org.springframework.web.bind.annotation.ExceptionHandler(MissingInformationInput.class)
    public ResponseEntity<ExceptionEntity> invalidInputsDto(MissingInformationInput e, HttpServletRequest request) {
        String error = "Error invalid input DTO";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = request.getRequestURI();
        ExceptionEntity err = new ExceptionEntity(new Date(), error, e.getMessage(), status.value(), path);
        return ResponseEntity.status(status).body(err);
    }
}
