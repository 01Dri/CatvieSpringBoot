package me.dri.Catvie.domain.adapters.services.mappers;

import me.dri.Catvie.domain.models.core.Film;
import me.dri.Catvie.domain.models.dto.director.DirectorResponseDTO;
import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;
import me.dri.Catvie.domain.models.dto.genre.GenreResponseDTO;
import me.dri.Catvie.domain.ports.interfaces.mappers.MapperFilmResponsePort;
import me.dri.Catvie.domain.ports.interfaces.mappers.MapperUserResponsePort;

import java.util.List;
import java.util.stream.Collectors;

public class MapperFilmResponseImpl implements MapperFilmResponsePort {


    private final MapperUserResponsePort mapperUserDomainPort;

    public MapperFilmResponseImpl(MapperUserResponsePort mapperUserDomainPort) {
        this.mapperUserDomainPort = mapperUserDomainPort;
    }



    @Override
    public List<FilmResponseDTO> convertListFilmToFilmResponseDTOList(List<Film> films) {
        return films.stream().
                map(f -> new FilmResponseDTO(f.getId(),
                f.getTitle(), f.getGenres().stream().map(g -> new GenreResponseDTO(g.getId(), g.getGenreName())).collect(Collectors.toSet()),
                f.getOriginalLanguage(), f.getReleaseDate(), f.getRuntime(), f.getDistributor(), f.getWriter(), f.getProductionCo(),
                f.getAverageRatingCritic(), f.getAverageRatingAudience(),
                new DirectorResponseDTO(f.getDirector().getId(), f.getDirector().getName()),
                f.getPosterUrl(), this.mapperUserDomainPort.convertUserToUserResponseFilmRequestDTO(f.getUser()), f.getLinks().toString())).collect(Collectors.toList());
    }



    @Override
    public FilmResponseDTO convertFilmToResponseDTO(Film film) {
        return new FilmResponseDTO(film.getId(),
                film.getTitle(), film.getGenres().stream().map(
                        g -> new GenreResponseDTO(g.getId(), g.getGenreName())).collect(Collectors.toSet()),
                film.getOriginalLanguage(), film.getReleaseDate(), film.getRuntime(),
                film.getDistributor(), film.getWriter(), film.getProductionCo(),
                film.getAverageRatingCritic(), film.getAverageRatingAudience(),
                new DirectorResponseDTO(film.getDirector().getId(), film.getDirector().getName()),
                film.getPosterUrl(), this.mapperUserDomainPort.convertUserToUserResponseFilmRequestDTO(film.getUser()), film.getLinks().toString());
    }


}
