package me.dri.Catvie.domain.ports.interfaces.notes;

import me.dri.Catvie.domain.models.dto.film.FilmDTO;

public interface NotesAudienceServicesPort {

    FilmDTO addNotesByFilmId(Double note, Long filmId);

}
