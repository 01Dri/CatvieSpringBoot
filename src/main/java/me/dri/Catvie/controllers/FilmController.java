package me.dri.Catvie.controllers;


import jakarta.servlet.http.HttpServletRequest;
import me.dri.Catvie.controllers.auth.AuthController;
import me.dri.Catvie.domain.consts.EndpointsConstants;
import me.dri.Catvie.domain.consts.LoggerConstants;
import me.dri.Catvie.domain.models.dto.film.FilmRequestDTO;
import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;
import me.dri.Catvie.domain.ports.interfaces.auth.TokenServicesPort;
import me.dri.Catvie.domain.ports.interfaces.film.FilmServicePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(EndpointsConstants.ENDPOINT_FILMS)
@CrossOrigin
public class FilmController {

    private final FilmServicePort filmServicePort;

    private final TokenServicesPort tokenServicesPort;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    @Autowired
    public FilmController(FilmServicePort filmServicePort, TokenServicesPort tokenServicesPort) {
        this.filmServicePort = filmServicePort;
        this.tokenServicesPort = tokenServicesPort;
    }

    @GetMapping(path = "/all" , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    ResponseEntity<List<FilmResponseDTO>> findAll () {
        logger.info(LoggerConstants.ACCESS_CONTROLLER_FILM_FIND_ALL);
        return ResponseEntity.ok(this.filmServicePort.findAll());
    }
    @GetMapping (path = "/byId/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE} )
    ResponseEntity<FilmResponseDTO> findById(@PathVariable Long id) {
        logger.info(LoggerConstants.ACCESS_CONTROLLER_FILM_FIND_BY_ID);
        return ResponseEntity.ok(this.filmServicePort.findById(id));
    }
    @GetMapping(path = "/byTitle/{title}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    ResponseEntity<FilmResponseDTO> findByTitle(@PathVariable String title) {
        logger.info(LoggerConstants.ACCESS_CONTROLLER_FILM_FIND_BY_TITLE);
        return ResponseEntity.ok(this.filmServicePort.findByTitle(title));
    }
    @PostMapping (path = "/create", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    ResponseEntity<FilmResponseDTO> create(@RequestBody FilmRequestDTO film, HttpServletRequest request, UriComponentsBuilder uriComponentsBuilder) throws NoSuchFieldException, IllegalAccessException {
        logger.info(LoggerConstants.ACCESS_CONTROLLER_FILM_CREATE);
        logger.info(LoggerConstants.GETTING_TOKEN_HEADER);
        var token = this.tokenServicesPort.getTokenForHeaders(request);
        logger.info(LoggerConstants.VALIDATING_TOKEN_HEADER);
        String subjectEmail = this.tokenServicesPort.validateToken(token);
        FilmResponseDTO filmEntityCreated = this.filmServicePort.create(film, subjectEmail);
        logger.info(LoggerConstants.GENERATING_URI);
        UriComponents uriComponents = uriComponentsBuilder.path(
                EndpointsConstants.ENDPOINT_FIND_FILMS + "/{id}").buildAndExpand(filmEntityCreated.id());
        return ResponseEntity.created(uriComponents.toUri()).body(filmEntityCreated);
    }
    @DeleteMapping("/deleteById/{id}")
    ResponseEntity<Long> deleteById(@PathVariable Long id) {
        logger.info(LoggerConstants.ACCESS_CONTROLLER_FILM_DELETE_BY_ID);
        this.filmServicePort.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping (path = "/edit", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    ResponseEntity<FilmResponseDTO> updateFilm(@RequestBody FilmRequestDTO dto) throws NoSuchFieldException, IllegalAccessException {
        logger.info(LoggerConstants.ACCESS_CONTROLLER_FILM_EDIT);
        return ResponseEntity.status(HttpStatus.OK).body(this.filmServicePort.update(dto));
    }
}


