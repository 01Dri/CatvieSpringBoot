package me.dri.Catvie.domain.ports.interfaces.mappers;

import me.dri.Catvie.domain.models.core.Film;
import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;
import me.dri.Catvie.infra.entities.FilmEntity;

import java.util.List;

public interface MapperFilmResponsePort {


    List<FilmResponseDTO> convertListFilmToFilmResponseDTOList(List<Film> films);

    FilmResponseDTO convertFilmToResponseDTO(Film film);

    Film convertFilmEntityToFilm(FilmEntity filmEntity);
    FilmEntity convertFilmToFilmEntity(Film film);

    List<Film>  convertListFilmEntityToFilmList(List<FilmEntity> filmsAll);
}
