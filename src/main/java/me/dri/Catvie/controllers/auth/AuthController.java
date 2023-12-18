package me.dri.Catvie.controllers.auth;

import jakarta.mail.MessagingException;
import me.dri.Catvie.domain.consts.EndpointsConstants;
import me.dri.Catvie.domain.consts.HttpConstants;
import me.dri.Catvie.domain.consts.LoggerConstants;
import me.dri.Catvie.domain.models.dto.auth.LoginDTO;
import me.dri.Catvie.domain.models.dto.auth.RegisterDTO;
import me.dri.Catvie.domain.models.dto.auth.RegisterResponseDTO;
import me.dri.Catvie.domain.models.dto.auth.TokenResponseDTO;
import me.dri.Catvie.domain.ports.interfaces.auth.AuthenticationServicePort;
import me.dri.Catvie.infra.ports.email.EmailServicePort;
import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping( EndpointsConstants.ENDPOINT_AUTH)
@CrossOrigin
public class AuthController {


    private final AuthenticationServicePort servicePort;
    private final EmailServicePort emailServicePort;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    public AuthController(AuthenticationServicePort servicePort, EmailServicePort emailServicePort) {
        this.servicePort = servicePort;
        this.emailServicePort = emailServicePort;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/register", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterDTO dto, UriComponentsBuilder uriComponentsBuilder) throws NoSuchFieldException, IllegalAccessException, MessagingException {
        logger.info(LoggerConstants.ACCESS_CONTROLLER_AUTH_REGISTER);
       // logger.info("Sending email");
       // this.emailServicePort.sendWelcomeMessage(dto.email()); // Send Email with AWS SES
       // logger.info("Email successfully sent");
        RegisterResponseDTO newEntity = this.servicePort.register(dto);
        logger.info(LoggerConstants.GENERATING_URI);
        UriComponents uriComponents = uriComponentsBuilder
                .path( EndpointsConstants.ENDPOINT_FIND_USERS + "/{id}").buildAndExpand(newEntity.id());
        return ResponseEntity.created(uriComponents.toUri()).body(newEntity);

    }

    @RequestMapping(method = RequestMethod.POST, path = "/login", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    ResponseEntity<TokenResponseDTO> login(@RequestBody LoginDTO dto) throws NoSuchFieldException, IllegalAccessException {
        logger.info(LoggerConstants.ACCESS_CONTROLLER_AUTH_LOGIN);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.servicePort.login(dto));
    }

}
