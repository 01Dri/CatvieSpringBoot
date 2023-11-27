package me.dri.Catvie.controllers;


import me.dri.Catvie.domain.models.dto.film.FilmDTO;
import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;
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


    @Autowired
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
    @RequestMapping(method = RequestMethod.GET, path = "/findByTitle/{title}")
    ResponseEntity<FilmDTO> findByTitle(@PathVariable String title) {
        return ResponseEntity.ok(this.filmServicePort.findByTitle(title));
    }


    @PostMapping(value = "/create")
    ResponseEntity<FilmResponseDTO> create(@RequestBody FilmDTO film) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.filmServicePort.create(film));
    }
}


