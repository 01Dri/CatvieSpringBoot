package me.dri.Catvie.infra.adapters.mapper;

import me.dri.Catvie.domain.models.entities.Director;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.models.entities.Genre;
import me.dri.Catvie.domain.models.entities.User;
import me.dri.Catvie.infra.entities.DirectorEntity;
import me.dri.Catvie.infra.entities.FilmEntity;
import me.dri.Catvie.infra.entities.GenreEntity;
import me.dri.Catvie.infra.ports.mappers.MapperFilmInfraPort;
import me.dri.Catvie.infra.ports.mappers.MapperUserInfraPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperFilmAdapter implements MapperFilmInfraPort {


    private final MapperUserInfraPort mapperUserPort;


    @Autowired
    public MapperFilmAdapter(MapperUserInfraPort mapperUserPort) {
        this.mapperUserPort = mapperUserPort;
    }


    @Override
    public Film convertyFilmEntityToFilm(FilmEntity filmEntity) {
        return new Film(filmEntity.getId(), filmEntity.getTitle(), filmEntity.getGenres().stream().map(genreEntity -> new Genre(genreEntity.getId(),
                genreEntity.getGenreName())).collect(Collectors.toSet()),
                filmEntity.getOriginalLanguage(), new Director(filmEntity.getDirector().getId(),
                filmEntity.getDirector().getName()), filmEntity.getWriter(),
                filmEntity.getReleaseDate(), filmEntity.getRuntime(), filmEntity.getDistributor(),
                filmEntity.getProductionCo(), filmEntity.getAverageRatingCritic(), filmEntity.getAverageRatingAudience(),
                filmEntity.getPosterUrl(), this.mapperUserPort.convertUserEntityToUser(filmEntity.getUser()));
    }


    @Override
    public List<Film> convertyListFilmsEntityToListFilm(List<FilmEntity> filmEntities) {
        return filmEntities.stream().map(filmEntity -> new Film(filmEntity.getId(), filmEntity.getTitle(), filmEntity.getGenres().stream().map(genreEntity -> new Genre(genreEntity.getId(), genreEntity.getGenreName())).collect(Collectors.toSet()), filmEntity.getOriginalLanguage(), new Director(filmEntity.getDirector().getId(), filmEntity.getDirector().getName()), filmEntity.getWriter(),
                filmEntity.getReleaseDate(), filmEntity.getRuntime(), filmEntity.getDistributor(), filmEntity.getProductionCo(),
                filmEntity.getAverageRatingCritic(), filmEntity.getAverageRatingAudience(), filmEntity.getPosterUrl(), this.mapperUserPort.convertUserEntityToUser(filmEntity.getUser()))).collect(Collectors.toList());
    }

    @Override
    public FilmEntity convertyFilmToFilmEntity(Film f) {
        return new FilmEntity(f.getId(), f.getTitle(), f.getGenres().stream().map(genre -> new GenreEntity(null,
                genre.getGenreName())).collect(Collectors.toSet()),
                f.getOriginalLanguage(), new DirectorEntity(f.getDirector().getId(), f.getDirector().getName()),
                f.getWriter(), f.getReleaseDate(), f.getRuntime(), f.getDistributor(),
                f.getProductioCo(), f.getAverageRatingCritic(), f.getAverageRatingAudience(), f.getPosterUrl(), null);
    }
}


