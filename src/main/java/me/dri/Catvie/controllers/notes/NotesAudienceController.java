package me.dri.Catvie.controllers.notes;

import jakarta.servlet.http.HttpServletRequest;
import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;
import me.dri.Catvie.domain.ports.interfaces.auth.TokenServicesPort;
import me.dri.Catvie.domain.ports.interfaces.notes.NotesAudienceServicesPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notes/v1")
@CrossOrigin
public class NotesAudienceController {


    private final NotesAudienceServicesPort notesService;

    private final TokenServicesPort tokenServicesPort;

    @Autowired
    public NotesAudienceController(NotesAudienceServicesPort notesService, TokenServicesPort tokenServicesPort) {
        this.notesService = notesService;
        this.tokenServicesPort = tokenServicesPort;
    }


    @RequestMapping(method = RequestMethod.POST, path = "/addNotesByFilmId/{note}/{idFilm}")
    public ResponseEntity<FilmResponseDTO> addNotesByFilmId(@PathVariable Double note, @PathVariable Long idFilm, HttpServletRequest request) {
        String token = this.tokenServicesPort.getTokenForHeaders(request);
        String subjectByToken = this.tokenServicesPort.validateToken(token);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.notesService.addNotesByFilmId(note, idFilm, subjectByToken));
    }



}
