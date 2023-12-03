package me.dri.Catvie.domain.ports.repositories;

import me.dri.Catvie.domain.models.entities.Film;

public interface NotesAudiencesPort {


    Film addNoteByFilmId(Double note, Long idFilm, String emailUser);
    Film addNoteByFilmTitle(Double note, String titleFilm, String emailUser);

    Film changeNoteByFilmId(Double newNote, Long idFilm, String  emailUser);
    Double getAverageNotesByFilmId(Long id);
    Double getAverageNotesByFilmName(String name);



}
