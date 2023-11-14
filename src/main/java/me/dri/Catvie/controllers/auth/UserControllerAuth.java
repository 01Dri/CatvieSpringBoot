package me.dri.Catvie.controllers.auth;

import me.dri.Catvie.domain.models.dto.auth.LoginDTO;
import me.dri.Catvie.domain.models.dto.auth.RegisterDTO;
import me.dri.Catvie.domain.models.dto.auth.RegisterResponseDTO;
import me.dri.Catvie.domain.models.dto.auth.TokenResponseDTO;
import me.dri.Catvie.domain.ports.interfaces.auth.AuthenticationServicePort;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(this.servicePort.register(dto));
    }

    @PostMapping(value = "/login")
    @CrossOrigin(origins = "*")
    ResponseEntity<TokenResponseDTO> login(@RequestBody LoginDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.servicePort.login(dto));
    }

}
