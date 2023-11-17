package me.dri.Catvie.domain.adapters.services.mappers;

import me.dri.Catvie.domain.models.entities.Director;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.models.entities.Genre;
import me.dri.Catvie.domain.ports.interfaces.film.MapperEntities;
import me.dri.Catvie.infra.entities.DirectorEntity;
import me.dri.Catvie.infra.entities.FilmEntity;
import me.dri.Catvie.infra.entities.GenreEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MapperEntitiesImpl implements MapperEntities {
    @Override
    public Set<Genre> convertGenreEntityToGenre(Set<GenreEntity> genreEntities) {
        return genreEntities.stream().map(g ->  new Genre(g.getId(), g.getGenreName())).collect(Collectors.toSet());
    }

    @Override
    public Set<GenreEntity> convertGenreToGenreEntity(Set<Genre> genreEntities) {
        return genreEntities.stream().map(g ->  new GenreEntity(g.getId(), g.getGenreName())).collect(Collectors.toSet());
    }

    @Override
    public Director convertyDirectorEntityToDirector(DirectorEntity director) {
        return  new Director(director.getId(), director.getName());
    }


    @Override
    public DirectorEntity convertyDirectorToDirectorEntity(Director director) {
        return  new DirectorEntity(director.getId(), director.getName());
    }


    @Override
    public Film convertyFilmEntityToFilm(FilmEntity filmEntity) {
        return new Film(filmEntity.getId(), filmEntity.getTitle() ,filmEntity.getGenres().stream().map(genreEntity -> new Genre(genreEntity.getId(), genreEntity.getGenreName())).collect(Collectors.toSet()), filmEntity.getOriginal_language(), new Director(filmEntity.getDirector().getId(), filmEntity.getDirector().getName()), filmEntity.getWriter(), filmEntity.getRelease_date(), filmEntity.getRuntime(), filmEntity.getDistributor(), filmEntity.getProduction_co(), filmEntity.getAverage_rating_critic(), filmEntity.getAverage_rating_audience(), filmEntity.getUrl());
    }


    @Override
    public List<Film> convertyListFilmsEntityToListFilm(List<FilmEntity> filmEntities) {
        List<Film> films = new ArrayList<>();
        for (FilmEntity f : filmEntities ) {
            Film film = new Film(f.getId(), f.getTitle(), convertGenreEntityToGenre(f.getGenres()), f.getOriginal_language(), convertyDirectorEntityToDirector(f.getDirector()), f.getWriter(), f.getRelease_date(), f.getRuntime(), f.getDistributor(), f.getProduction_co(), f.getAverage_rating_critic(), f.getAverage_rating_audience(), f.getUrl());
            films.add(film);
        }
        return films;
    }

    @Override
    public FilmEntity convertyFilmToFilmEntity(Film f) {
        return new FilmEntity(null, f.getTitle(), f.getGenres().stream().map(genre -> new GenreEntity(null, genre.getGenreName())).collect(Collectors.toSet()), f.getOriginal_language(), convertyDirectorToDirectorEntity(f.getDirectorEntity()), f.getWriter(), f.getRelease_date(), f.getRuntime(), f.getDistributor(), f.getProduction_co(), f.getAverage_rating_critic(), f.getAverage_rating_audience(), f.getUrl());
    }


}
