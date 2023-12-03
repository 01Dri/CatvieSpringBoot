package me.dri.Catvie.domain.ports.interfaces.notes;

import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;

public interface NotesAudienceServicesPort {

    FilmResponseDTO addNotesByFilmId(Double note, Long filmId, Long idUser);

}
