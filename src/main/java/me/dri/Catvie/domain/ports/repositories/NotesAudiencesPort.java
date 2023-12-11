package me.dri.Catvie.domain.ports.repositories;

import me.dri.Catvie.domain.models.entities.NotesAudience;

import java.util.List;

public interface NotesAudiencesPort {


    NotesAudience addNoteByFilmId(Double note, Long idFilm, String emailUser);
    NotesAudience addNoteByFilmTitle(Double note, String titleFilm, String emailUser);

    NotesAudience changeNoteByFilmId(Double newNote, Long idFilm, String  emailUser, Long idNote);
    Double getAverageNotesByFilmId(Long id);

    List<NotesAudience> findAllNotes();




}
