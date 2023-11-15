package me.dri.Catvie.domain.ports.interfaces.film;

import me.dri.Catvie.domain.models.dto.director.DirectorCreateDTO;
import me.dri.Catvie.domain.models.dto.director.DirectorResponseDTO;
import me.dri.Catvie.domain.models.dto.film.FilmDTO;
import me.dri.Catvie.domain.models.dto.genre.GenreDTO;
import me.dri.Catvie.domain.models.entities.Director;
import me.dri.Catvie.domain.models.entities.Film;

import java.util.List;
import java.util.Set;

public interface MapperEntitiesPort {


    FilmDTO convertFilmToDto(Film film);
    List<FilmDTO> convertListFilmToListDto(List<Film> films);

    Film convertFilmDtoToFilm(FilmDTO filmDTO, Set<GenreDTO> genres, DirectorResponseDTO director);

}
