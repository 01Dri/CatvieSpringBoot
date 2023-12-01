package me.dri.Catvie.domain.ports.repositories;

import me.dri.Catvie.domain.models.entities.Film;

public interface NotesAudiencesPort {


    Long addNote(Double note, Film film);
    Double getAverageNotesByFilmId(Long id);

    Double getAverageNotesByFilmName(String name);


}
