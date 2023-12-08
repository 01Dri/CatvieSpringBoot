package me.dri.Catvie.controllers.user;


import me.dri.Catvie.domain.models.dto.user.UserResponseDTO;
import me.dri.Catvie.domain.ports.interfaces.user.UserServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/v1")
@CrossOrigin
public class UserController {

    private final UserServicePort service;


    @Autowired
    public UserController(UserServicePort service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/findById/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.service.findById(id));
    }
}
