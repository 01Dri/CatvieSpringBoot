package me.dri.Catvie.controllers;


import me.dri.Catvie.domain.consts.EndpointsConstants;
import me.dri.Catvie.domain.models.dto.director.DirectorResponseDTO;
import me.dri.Catvie.domain.ports.interfaces.director.DirectorServicePort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(EndpointsConstants.ENDPOINT_DIRECTOR)
@CrossOrigin
public class DirectorController  {

    private final DirectorServicePort servicePort;

    public DirectorController(DirectorServicePort servicePort) {
        this.servicePort = servicePort;
    }

    @GetMapping(value = "/byName/{titleFilm}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    ResponseEntity<DirectorResponseDTO> findByName(@PathVariable String titleFilm) {
        return ResponseEntity.ok().body(this.servicePort.findByName(titleFilm));
    }
}
