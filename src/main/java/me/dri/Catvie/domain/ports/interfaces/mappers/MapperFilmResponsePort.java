package me.dri.Catvie.domain.ports.interfaces.mappers;

import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;
import me.dri.Catvie.domain.models.core.Film;

import java.util.List;

public interface MapperFilmResponsePort {


    List<FilmResponseDTO> convertListFilmToFilmResponseDTOList(List<Film> films);

    FilmResponseDTO convertFilmToResponseDTO(Film film);


}
