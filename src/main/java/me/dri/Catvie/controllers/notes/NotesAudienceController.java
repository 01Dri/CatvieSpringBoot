package me.dri.Catvie.controllers.notes;

import jakarta.servlet.http.HttpServletRequest;
import me.dri.Catvie.domain.models.dto.notes.NotesResponseDTO;
import me.dri.Catvie.domain.ports.interfaces.auth.TokenServicesPort;
import me.dri.Catvie.domain.ports.interfaces.notes.NotesAudienceServicesPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<NotesResponseDTO> addNotesByFilmId(@PathVariable Double note, @PathVariable Long idFilm, HttpServletRequest request) {
        String token = this.tokenServicesPort.getTokenForHeaders(request);
        String subjectByToken = this.tokenServicesPort.validateToken(token);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.notesService.addNotesByFilmId(note, idFilm, subjectByToken));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/addNotesByFilmTitle/{note}/{title}")
    public ResponseEntity<NotesResponseDTO> addNotesByFilmId(@PathVariable Double note, @PathVariable String title, HttpServletRequest request) {
        String token = this.tokenServicesPort.getTokenForHeaders(request);
        String subjectByToken = this.tokenServicesPort.validateToken(token);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.notesService.addNotesByFilmTitle(note, title, subjectByToken));
    }
    @RequestMapping(method = RequestMethod.PATCH, path = "/changeNoteByFilmId/{newNote}/{filmId}/{idNote}")
    public ResponseEntity<NotesResponseDTO> changeNoteByFilmId(@PathVariable Double newNote, @PathVariable Long filmId, @PathVariable Long idNote, HttpServletRequest request) {
        String token = this.tokenServicesPort.getTokenForHeaders(request);
        String subjectByToken = this.tokenServicesPort.validateToken(token);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.notesService.changeNoteByFilmId(newNote, filmId, subjectByToken, idNote));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/findAll")
    public ResponseEntity<List<NotesResponseDTO>> findAll() {
        return ResponseEntity.ok().body(this.notesService.findAllNotes());
    }
}


