package me.dri.Catvie.controllers;


import jakarta.servlet.http.HttpServletRequest;
import me.dri.Catvie.domain.consts.EndpointsConstants;
import me.dri.Catvie.domain.models.dto.film.FilmRequestDTO;
import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;
import me.dri.Catvie.domain.ports.interfaces.auth.TokenServicesPort;
import me.dri.Catvie.domain.ports.interfaces.film.FilmServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(EndpointsConstants.ENDPOINT_FILMS)
@CrossOrigin
public class FilmController {

    private final FilmServicePort filmServicePort;

    private final TokenServicesPort tokenServicesPort;


    @Autowired
    public FilmController(FilmServicePort filmServicePort, TokenServicesPort tokenServicesPort) {
        this.filmServicePort = filmServicePort;
        this.tokenServicesPort = tokenServicesPort;
    }

    @GetMapping(path = "/all" , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    ResponseEntity<List<FilmResponseDTO>> findAll () {
        return ResponseEntity.ok(this.filmServicePort.findAll());
    }
    @GetMapping (path = "/byId/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE} )
    ResponseEntity<FilmResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.filmServicePort.findById(id));
    }
    @GetMapping(path = "/byTitle/{title}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    ResponseEntity<FilmResponseDTO> findByTitle(@PathVariable String title) {
        return ResponseEntity.ok(this.filmServicePort.findByTitle(title));
    }
    @PostMapping (path = "/create", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    ResponseEntity<FilmResponseDTO> create(@RequestBody FilmRequestDTO film, HttpServletRequest request) throws NoSuchFieldException, IllegalAccessException {
        var token = this.tokenServicesPort.getTokenForHeaders(request);
        String subjectEmail = this.tokenServicesPort.validateToken(token);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.filmServicePort.create(film, subjectEmail));
    }
    @DeleteMapping("/deleteById/{id}")
    ResponseEntity<Long> deleteById(@PathVariable Long id) {
        this.filmServicePort.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping (path = "/edit", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    ResponseEntity<FilmResponseDTO> updateFilm(@RequestBody FilmRequestDTO dto) throws NoSuchFieldException, IllegalAccessException {
        return ResponseEntity.status(HttpStatus.OK).body(this.filmServicePort.update(dto));
    }
}


