package me.dri.Catvie.controllers.user;


import me.dri.Catvie.controllers.auth.AuthController;
import me.dri.Catvie.domain.consts.EndpointsConstants;
import me.dri.Catvie.domain.consts.HttpConstants;
import me.dri.Catvie.domain.consts.LoggerConstants;
import me.dri.Catvie.domain.models.dto.user.UserResponseDTO;
import me.dri.Catvie.domain.ports.interfaces.user.UserServicePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(EndpointsConstants.ENDPOINT_USERS)
@CrossOrigin
public class UserController {

    private final UserServicePort service;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


    @Autowired
    public UserController(UserServicePort service) {
        this.service = service;
    }

    @GetMapping(path = "/byId/{id}", produces = {HttpConstants.CONTENT_TYPE_JSON, HttpConstants.CONTENT_TYPE_XML})
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        logger.info(LoggerConstants.ACCESS_CONTROLLER_USER_FIND_BY_ID);
        return ResponseEntity.ok().body(this.service.findById(id));
    }


}
