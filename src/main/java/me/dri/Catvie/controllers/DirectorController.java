package me.dri.Catvie.controllers;


import me.dri.Catvie.domain.models.dto.director.DirectorDTO;
import me.dri.Catvie.domain.models.dto.director.DirectorResponseDTO;
import me.dri.Catvie.domain.ports.interfaces.director.DirectorServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/director/v1")
public class DirectorController  {

    private final DirectorServicePort servicePort;

    public DirectorController(DirectorServicePort servicePort) {
        this.servicePort = servicePort;
    }

    @GetMapping(value = "/findByName/{name}")
    ResponseEntity<DirectorResponseDTO> findByName(@PathVariable String name) {
        return ResponseEntity.ok().body(this.servicePort.findByName(name));
    }
}
