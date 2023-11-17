package me.dri.Catvie.controllers;


import me.dri.Catvie.domain.models.dto.film.FilmDTO;
import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;
import me.dri.Catvie.domain.ports.interfaces.film.FilmServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/film/v1")
public class FilmController {


    private final FilmServicePort filmServicePort;

    public FilmController(FilmServicePort filmServicePort) {
        this.filmServicePort = filmServicePort;
    }


    @RequestMapping(method = RequestMethod.GET, path = "/findAll")
    List<FilmDTO> findAll () {
        return this.filmServicePort.findAll();
    }

    @GetMapping(value = "/findById/{id}")
    ResponseEntity<FilmDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.filmServicePort.findById(id));
    }
    @GetMapping(value = "/findByTitle/{title}")
    ResponseEntity<FilmDTO> findById(@PathVariable String title) {
        return ResponseEntity.ok(this.filmServicePort.findByTitle(title));
    }

    @PostMapping(value = "/create")
    ResponseEntity<FilmResponseDTO> create(@RequestBody FilmDTO film) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.filmServicePort.create(film));
    }


}
