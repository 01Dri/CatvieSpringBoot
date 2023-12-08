package me.dri.Catvie.controllers.auth;

import me.dri.Catvie.domain.models.dto.auth.LoginDTO;
import me.dri.Catvie.domain.models.dto.auth.RegisterDTO;
import me.dri.Catvie.domain.models.dto.auth.RegisterResponseDTO;
import me.dri.Catvie.domain.models.dto.auth.TokenResponseDTO;
import me.dri.Catvie.domain.ports.interfaces.auth.AuthenticationServicePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/v1")
@CrossOrigin
public class UserControllerAuth {


    private final AuthenticationServicePort servicePort;
    private static final Logger logger = LoggerFactory.getLogger(UserControllerAuth.class);

    public UserControllerAuth(AuthenticationServicePort servicePort) {
        this.servicePort = servicePort;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/register")
    ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.servicePort.register(dto));

    }

    @RequestMapping(method = RequestMethod.POST, path = "/login")
    ResponseEntity<TokenResponseDTO> login(@RequestBody LoginDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.servicePort.login(dto));
    }

}
