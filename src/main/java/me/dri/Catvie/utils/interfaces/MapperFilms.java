package me.dri.Catvie.utils.interfaces;

import me.dri.Catvie.domain.models.core.Film;
import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;
import me.dri.Catvie.infra.entities.FilmEntity;

import java.util.List;

public interface MapperFilms {


    List<FilmResponseDTO> convertListFilmToFilmResponseDTOList(List<Film> films);

    FilmResponseDTO convertFilmToResponseDTO(Film film);

    Film convertFilmEntityToFilm(FilmEntity filmEntity);
    FilmEntity convertFilmToFilmEntity(Film film);

    List<Film>  convertListFilmEntityToFilmList(List<FilmEntity> filmsAll);
}
