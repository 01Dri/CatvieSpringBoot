package me.dri.Catvie.controllers.auth;

import me.dri.Catvie.domain.models.dto.RegisterDTO;
import me.dri.Catvie.domain.models.dto.RegisterResponseDTO;
import me.dri.Catvie.domain.ports.interfaces.AuthenticationServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserControllerAuth {


    private final AuthenticationServicePort servicePort;

    public UserControllerAuth(AuthenticationServicePort servicePort) {
        this.servicePort = servicePort;
    }

    @PostMapping (value = "/register")
    @CrossOrigin(origins = "*")
    ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterDTO dto) {
        return ResponseEntity.ok(this.servicePort.register(dto));
    }
}
