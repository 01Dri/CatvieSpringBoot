package me.dri.Catvie.domain.adapters.services.mappers;

import me.dri.Catvie.domain.models.dto.director.DirectorCreateDTO;
import me.dri.Catvie.domain.models.dto.director.DirectorDTO;
import me.dri.Catvie.domain.models.dto.director.DirectorResponseDTO;
import me.dri.Catvie.domain.models.dto.film.FilmDTO;
import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;
import me.dri.Catvie.domain.models.dto.genre.GenreDTO;
import me.dri.Catvie.domain.models.entities.Director;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.models.entities.Genre;
import me.dri.Catvie.domain.ports.interfaces.film.MapperEntitiesPort;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MapperFilmImpl implements MapperEntitiesPort {


    @Override
    public FilmDTO convertFilmToDto(Film film) {
        Set<GenreDTO> genreDTOS = film.getGenres().stream().map(genre -> new GenreDTO(genre.getGenreName())).collect(Collectors.toSet());
       return new FilmDTO(film.getTitle(), genreDTOS, film.getOriginal_language(), new DirectorCreateDTO(film.getDirectorEntity().getName()), film.getWriter(), film.getRelease_date(), film.getRuntime(), film.getDistributor(), film.getProduction_co(), film.getAverage_rating_critic(), film.getAverage_rating_audience(), film.getUrl());
    }

    @Override
    public List<FilmDTO> convertListFilmToListDto(List<Film> films) {
        return films.stream().map(film -> new FilmDTO(film.getTitle(), film.getGenres().stream().map(genre -> new GenreDTO(genre.getGenreName())).collect(Collectors.toSet()), film.getOriginal_language(),  new DirectorCreateDTO(film.getDirectorEntity().getName()), film.getWriter(), film.getRelease_date(), film.getRuntime(), film.getDistributor(), film.getProduction_co(), film.getAverage_rating_critic(), film.getAverage_rating_audience(), film.getUrl())).collect(Collectors.toList());
    }

    @Override
    public Film convertFilmDtoToFilm(FilmDTO filmDTO, Set<GenreDTO> genres, DirectorResponseDTO dto) {
        return new Film(null, filmDTO.title(), genres.stream().map(genreDTO -> new Genre(null, genreDTO.genreName())).collect(Collectors.toSet()), filmDTO.original_language(), new Director(null, dto.name()), filmDTO.writer(), filmDTO.release_date(), filmDTO.runtime(), filmDTO.distributor(), filmDTO.production_co(), filmDTO.average_rating_critic(), filmDTO.average_rating_audience(), filmDTO.url());
    }

    @Override
    public FilmResponseDTO convertFilmToResponseDTO(Film film) {
        return new FilmResponseDTO(
                film.getTitle(), film.getGenres().stream().map(g -> new GenreDTO(g.getGenreName())).collect(Collectors.toSet()), film.getOriginal_language(),
                film.getRelease_date(), film.getRuntime(), film.getDistributor(), film.getWriter(), film.getProduction_co(),
                film.getAverage_rating_critic(), film.getAverage_rating_audience(), film.getUrl());
    }

}
