package me.dri.Catvie.controllers.notes;

import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;
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


    @Autowired
    public NotesAudienceController(NotesAudienceServicesPort notesService) {
        this.notesService = notesService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/addNotesByFilmId/{note}/{idFilm}/{idUser}")
    public ResponseEntity<FilmResponseDTO> addNotesByFilmId(@PathVariable Double note, @PathVariable Long idFilm, @PathVariable Long idUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.notesService.addNotesByFilmId(note, idFilm, idUser));
    }
}
