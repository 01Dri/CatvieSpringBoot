package me.dri.Catvie.controllers;


import jakarta.servlet.http.HttpServletRequest;
import me.dri.Catvie.domain.models.dto.film.FilmDTO;
import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;
import me.dri.Catvie.domain.ports.interfaces.auth.TokenServicesPort;
import me.dri.Catvie.domain.ports.interfaces.film.FilmServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/film/v1")
@CrossOrigin
public class FilmController {

    private final FilmServicePort filmServicePort;

    private final TokenServicesPort tokenServicesPort;



    @Autowired
    public FilmController(FilmServicePort filmServicePort, TokenServicesPort tokenServicesPort) {
        this.filmServicePort = filmServicePort;
        this.tokenServicesPort = tokenServicesPort;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/findAll")
    List<FilmResponseDTO> findAll () {
        return this.filmServicePort.findAll();
    }
    @GetMapping(value = "/findById/{id}")
    ResponseEntity<FilmResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.filmServicePort.findById(id));
    }
    @RequestMapping(method = RequestMethod.GET, path = "/findByTitle/{title}")
    ResponseEntity<FilmResponseDTO> findByTitle(@PathVariable String title) {
        return ResponseEntity.ok(this.filmServicePort.findByTitle(title));
    }
    @RequestMapping(method = RequestMethod.POST, path = "/create")
    ResponseEntity<FilmResponseDTO> create(@RequestBody FilmDTO film, HttpServletRequest request) {
        var token = this.tokenServicesPort.getTokenForHeaders(request);
        String subjectEmail = this.tokenServicesPort.validateToken(token);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.filmServicePort.create(film, subjectEmail));
    }
    @RequestMapping(method = RequestMethod.DELETE, path = "/deleteById/{id}")
    ResponseEntity<Long> deleteById(@PathVariable Long id) {
        this.filmServicePort.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/update")
    ResponseEntity<FilmResponseDTO> updateFilm(@RequestBody FilmDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.filmServicePort.update(dto));
    }


}


