package me.dri.Catvie.domain.ports.interfaces.mappers;

import me.dri.Catvie.domain.models.dto.director.DirectorDTO;
import me.dri.Catvie.domain.models.dto.film.FilmDTO;
import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;
import me.dri.Catvie.domain.models.dto.genre.GenreDTO;
import me.dri.Catvie.domain.models.entities.Film;

import java.util.List;
import java.util.Set;

public interface MapperFilmDomainPort {


    FilmDTO convertFilmToDto(Film film);
    List<FilmResponseDTO> convertListFilmToListDto(List<Film> films);

    Film convertFilmDtoToFilm(FilmDTO filmDTO, Set<GenreDTO> genres, DirectorDTO director);

    FilmResponseDTO convertFilmToResponseDTO(Film film);


}