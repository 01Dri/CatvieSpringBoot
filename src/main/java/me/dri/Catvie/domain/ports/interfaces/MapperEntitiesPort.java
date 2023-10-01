package me.dri.Catvie.domain.ports.interfaces;

import me.dri.Catvie.domain.models.dto.FilmDTO;
import me.dri.Catvie.domain.models.entities.Film;

import java.util.List;

public interface MapperEntitiesPort {


    FilmDTO convertFilmToDto(Film film);
    List<FilmDTO> convertListFilmToListDto(List<Film> films);

    Film convertFilmDtoToFilm(FilmDTO filmDTO);

}
