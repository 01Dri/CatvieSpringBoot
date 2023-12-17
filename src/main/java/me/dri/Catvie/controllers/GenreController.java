package me.dri.Catvie.controllers;


import me.dri.Catvie.domain.consts.EndpointsConstants;
import me.dri.Catvie.domain.models.dto.genre.GenreResponseDTO;
import me.dri.Catvie.domain.ports.interfaces.genre.GenreServicesPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(EndpointsConstants.ENDPOINT_GENRES)
@CrossOrigin
public class GenreController {

    private final GenreServicesPort servicesPort;

    public GenreController(GenreServicesPort servicesPort) {
        this.servicesPort = servicesPort;
    }

    @GetMapping(value = "/byName/{name}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    ResponseEntity<GenreResponseDTO> findByName(@PathVariable String name) {
        return ResponseEntity.ok().body(this.servicesPort.findByName(name));
    }
    @GetMapping(path = "/all", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    ResponseEntity<Set<GenreResponseDTO>> findAll() {
        return ResponseEntity.ok().body(this.servicesPort.findAll());
    }
}
