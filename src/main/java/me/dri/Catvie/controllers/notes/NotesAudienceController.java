package me.dri.Catvie.controllers.notes;

import jakarta.servlet.http.HttpServletRequest;
import me.dri.Catvie.controllers.auth.AuthController;
import me.dri.Catvie.domain.consts.EndpointsConstants;
import me.dri.Catvie.domain.consts.LoggerConstants;
import me.dri.Catvie.domain.models.dto.notes.NotesResponseDTO;
import me.dri.Catvie.domain.ports.interfaces.auth.TokenServicesPort;
import me.dri.Catvie.domain.ports.interfaces.notes.NotesAudienceServicesPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping( EndpointsConstants.ENDPOINT_NOTES)
@CrossOrigin
public class NotesAudienceController {

    private final NotesAudienceServicesPort notesService;

    private final TokenServicesPort tokenServicesPort;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    public NotesAudienceController(NotesAudienceServicesPort notesService, TokenServicesPort tokenServicesPort) {
        this.notesService = notesService;
        this.tokenServicesPort = tokenServicesPort;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/addById/{note}/{idFilm}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<NotesResponseDTO> addNotesByFilmId(@PathVariable Double note, @PathVariable Long idFilm, HttpServletRequest request, UriComponentsBuilder uriComponentsBuilder) {
        logger.info(LoggerConstants.ACCESS_CONTROLLER_NOTES_ADD_BY_ID);
        logger.info(LoggerConstants.GETTING_TOKEN_HEADER);
        String token = this.tokenServicesPort.getTokenForHeaders(request);
        logger.info(LoggerConstants.VALIDATING_TOKEN_HEADER);
        String subjectByToken = this.tokenServicesPort.validateToken(token);
        NotesResponseDTO notesEntityCreated = this.notesService.addNotesByFilmId(note, idFilm, subjectByToken);
        logger.info(LoggerConstants.GENERATING_URI);
        UriComponents uriComponents = uriComponentsBuilder.path(EndpointsConstants.ENDPOINT_NOTES + "byId/{id}" + notesEntityCreated.idNote()).buildAndExpand(notesEntityCreated.idNote());
        return ResponseEntity.created(uriComponents.toUri()).body(notesEntityCreated);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/addByTitle/{note}/{title}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<NotesResponseDTO> addNotesByFilmTitle(@PathVariable Double note, @PathVariable String title, HttpServletRequest request, UriComponentsBuilder uriComponentsBuilder) {
        logger.info(LoggerConstants.ACCESS_CONTROLLER_NOTES_ADD_BY_TITLE);
        logger.info(LoggerConstants.GETTING_TOKEN_HEADER);
        String token = this.tokenServicesPort.getTokenForHeaders(request);
        logger.info(LoggerConstants.VALIDATING_TOKEN_HEADER);
        String subjectByToken = this.tokenServicesPort.validateToken(token);
        NotesResponseDTO notesEntityCreated = this.notesService.addNotesByFilmTitle(note, title, subjectByToken);
        logger.info(LoggerConstants.GENERATING_URI);
        UriComponents uriComponents = uriComponentsBuilder.path(EndpointsConstants.ENDPOINT_NOTES + "byId/{id}" + notesEntityCreated.idNote()).buildAndExpand(notesEntityCreated.idNote());
        return ResponseEntity.created(uriComponents.toUri()).body(notesEntityCreated);
    }
    @RequestMapping(method = RequestMethod.PATCH, path = "/change/{newNote}/{idNote}/{idFilm}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<NotesResponseDTO> changeNoteByFilmId(@PathVariable Double newNote, @PathVariable Long idNote, @PathVariable Long filmId, HttpServletRequest request, UriComponentsBuilder uriComponentsBuilder) {
        logger.info(LoggerConstants.ACCESS_CONTROLLER_NOTES_CHANGE_BY_ID);
        logger.info(LoggerConstants.GETTING_TOKEN_HEADER);
        String token = this.tokenServicesPort.getTokenForHeaders(request);
        logger.info(LoggerConstants.VALIDATING_TOKEN_HEADER);
        String subjectByToken = this.tokenServicesPort.validateToken(token);
        return ResponseEntity.status(HttpStatus.OK).body(this.notesService.changeNoteByFilmId(newNote, filmId, subjectByToken, idNote));
    }
    @RequestMapping(method = RequestMethod.GET, path = "/all", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<NotesResponseDTO>> findAll() {
        logger.info(LoggerConstants.ACCESS_CONTROLLER_NOTES_FIND_ALL);
        return ResponseEntity.ok().body(this.notesService.findAllNotes());
    }
}


