package me.dri.Catvie.controllers;


import me.dri.Catvie.controllers.auth.AuthController;
import me.dri.Catvie.domain.consts.EndpointsConstants;
import me.dri.Catvie.domain.consts.LoggerConstants;
import me.dri.Catvie.domain.models.dto.genre.GenreResponseDTO;
import me.dri.Catvie.domain.ports.interfaces.genre.GenreServicesPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(EndpointsConstants.ENDPOINT_GENRES)
@CrossOrigin
public class GenreController {

    private final GenreServicesPort servicesPort;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    public GenreController(GenreServicesPort servicesPort) {
        this.servicesPort = servicesPort;
    }
    
    @GetMapping(path = "/all", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    ResponseEntity<Set<GenreResponseDTO>> findAll() {
        logger.info(LoggerConstants.ACCESS_CONTROLLER_GENRE_FIND_ALL);
        return ResponseEntity.ok().body(this.servicesPort.findAll());
    }
}
