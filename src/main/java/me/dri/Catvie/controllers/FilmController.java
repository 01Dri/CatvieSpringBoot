package me.dri.Catvie.controllers;


import me.dri.Catvie.domain.models.dto.FilmDTO;
import me.dri.Catvie.domain.ports.interfaces.FilmServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/film/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FilmController {


    private final FilmServicePort filmServicePort;

    public FilmController(FilmServicePort filmServicePort) {
        this.filmServicePort = filmServicePort;
    }


    @GetMapping(value = "/findAll")
    @CrossOrigin(origins = "*")
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

}
