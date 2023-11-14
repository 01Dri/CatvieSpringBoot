package me.dri.Catvie.controllers;


import me.dri.Catvie.domain.models.dto.genre.GenreDTO;
import me.dri.Catvie.domain.ports.interfaces.genre.GenreServicesPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/genre/v1")
public class GenreController {

    private final GenreServicesPort servicesPort;

    public GenreController(GenreServicesPort servicesPort) {
        this.servicesPort = servicesPort;
    }

    @GetMapping(value = "/findGenreByName/{name}")
    ResponseEntity<GenreDTO> findByName(@PathVariable String name) {
        return ResponseEntity.ok().body(this.servicesPort.findByName(name));
    }
    @GetMapping(value = "/findAll")
    ResponseEntity<Set<GenreDTO>> findByName() {
        return ResponseEntity.ok().body(this.servicesPort.findAll());
    }
}
