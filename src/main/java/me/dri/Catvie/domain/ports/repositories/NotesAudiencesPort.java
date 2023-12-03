package me.dri.Catvie.domain.ports.repositories;

import me.dri.Catvie.domain.models.entities.Film;

public interface NotesAudiencesPort {


    Film addNoteByFilmId(Double note, Long idFilm, Long idUser);
    Double getAverageNotesByFilmId(Long id);

    Double getAverageNotesByFilmName(String name);


}
