package me.dri.Catvie.domain.adapters.services.mappers;

import me.dri.Catvie.domain.models.dto.director.DirectorDTO;
import me.dri.Catvie.domain.models.dto.film.FilmDTO;
import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;
import me.dri.Catvie.domain.models.dto.genre.GenreDTO;
import me.dri.Catvie.domain.models.entities.Director;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.models.entities.Genre;
import me.dri.Catvie.domain.ports.interfaces.mappers.MapperFilmDomainPort;
import me.dri.Catvie.domain.ports.interfaces.mappers.MapperUserDomainPort;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MapperFilmImpl implements MapperFilmDomainPort {


    private final MapperUserDomainPort mapperUserDomainPort;

    public MapperFilmImpl(MapperUserDomainPort mapperUserDomainPort) {
        this.mapperUserDomainPort = mapperUserDomainPort;
    }

    @Override
    public FilmDTO convertFilmToDto(Film film) {
        Set<GenreDTO> genreDTOS = film.getGenres().stream().map(genre -> new GenreDTO(genre.getGenreName())).collect(Collectors.toSet());
       return new FilmDTO(film.getTitle(), genreDTOS, film.getOriginalLanguage(),
               new DirectorDTO(film.getDirector().getName(), film.getDirector().getFilms()), film.getWriter(),
               film.getReleaseDate(), film.getRuntime(), film.getDistributor(),
               film.getProductioCo(),
               film.getPosterUrl());
    }

    @Override
    public List<FilmResponseDTO> convertListFilmToListDto(List<Film> films) {
        return films.stream().map(f -> new FilmResponseDTO(f.getId(),
                f.getTitle(), f.getGenres().stream().map(g -> new GenreDTO(g.getGenreName())).collect(Collectors.toSet()),
                f.getOriginalLanguage(), f.getReleaseDate(), f.getRuntime(), f.getDistributor(), f.getWriter(), f.getProductioCo(),
                f.getAverageRatingCritic(), f.getAverageRatingAudience(), f.getPosterUrl(), this.mapperUserDomainPort.convertUserToUserResponseFilmDTO(f.getUser())
        )).collect(Collectors.toList());
    }

    @Override
    public Film convertFilmDtoToFilm(FilmDTO filmDTO, Set<GenreDTO> genres, DirectorDTO dto) {
        return new Film(null, filmDTO.title(), genres.stream().map(genreDTO -> new Genre(null, genreDTO.genreName())).collect(Collectors.toSet()),
                filmDTO.originalLanguage(), new Director(null, dto.name()), filmDTO.writer(), filmDTO.releaseDate(),
                filmDTO.runtime(), filmDTO.distributor(), filmDTO.productionCo(), 0.0,
                0.0, filmDTO.posterUrl(), null);
    }

    @Override
    public FilmResponseDTO convertFilmToResponseDTO(Film film) {
        return new FilmResponseDTO(film.getId(),
                film.getTitle(), film.getGenres().stream().map(g -> new GenreDTO(g.getGenreName())).collect(Collectors.toSet()), film.getOriginalLanguage(),
                film.getReleaseDate(), film.getRuntime(), film.getDistributor(), film.getWriter(), film.getProductioCo(),
                film.getAverageRatingCritic(), film.getAverageRatingAudience(), film.getPosterUrl(), this.mapperUserDomainPort.convertUserToUserResponseFilmDTO(film.getUser()));
    }

}
