package me.dri.Catvie.domain.ports.interfaces.notes;

import me.dri.Catvie.domain.models.dto.notes.NotesResponseDTO;

import java.util.List;

public interface NotesAudienceServicesPort {

    NotesResponseDTO addNotesByFilmId(Double note, Long filmId, String emailUser);

    NotesResponseDTO addNotesByFilmTitle(Double note, String filmTitle, String emailUser);

    NotesResponseDTO changeNoteByFilmId(Double newNote, Long filmId, String emailUser, Long idUser);

    List<NotesResponseDTO> findAllNotes();

}
