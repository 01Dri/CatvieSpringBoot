package me.dri.Catvie.domain.models.dto.notes;

import me.dri.Catvie.domain.models.dto.film.FilmDTO;
import me.dri.Catvie.domain.models.dto.user.UserDTO;

public record NotesAudienceFilmDto(FilmDTO filmDTO, UserDTO userDTO, Double note) {
}
