package me.dri.Catvie.controllers.user;


import me.dri.Catvie.domain.consts.EndpointsConstants;
import me.dri.Catvie.domain.consts.HttpConstants;
import me.dri.Catvie.domain.models.dto.user.UserResponseDTO;
import me.dri.Catvie.domain.ports.interfaces.user.UserServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(EndpointsConstants.ENDPOINT_USERS)
@CrossOrigin
public class UserController {

    private final UserServicePort service;


    @Autowired
    public UserController(UserServicePort service) {
        this.service = service;
    }

    @GetMapping(path = "/byId", produces = {HttpConstants.CONTENT_TYPE_JSON, HttpConstants.CONTENT_TYPE_XML})
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.service.findById(id));
    }


}
