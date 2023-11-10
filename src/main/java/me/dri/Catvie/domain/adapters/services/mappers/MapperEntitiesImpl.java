package me.dri.Catvie.domain.adapters.services.mappers;

import me.dri.Catvie.domain.models.entities.Director;
import me.dri.Catvie.domain.models.entities.Distributor;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.models.entities.Genre;
import me.dri.Catvie.domain.ports.interfaces.MapperEntities;
import me.dri.Catvie.infra.entities.DirectorEntity;
import me.dri.Catvie.infra.entities.DistributorEntity;
import me.dri.Catvie.infra.entities.FilmEntity;
import me.dri.Catvie.infra.entities.GenreEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapperEntitiesImpl implements MapperEntities {
    @Override
    public List<Genre> convertGenreEntityToGenre(List<GenreEntity> genreEntities) {
        return genreEntities.stream().map(g ->  new Genre(g.getId(), g.getGenreName())).toList();
    }

    @Override
    public List<GenreEntity> convertGenreToGenreEntity(List<Genre> genreEntities) {
        return genreEntities.stream().map(g ->  new GenreEntity(g.getId(), g.getGenreName())).toList();
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
    public Distributor convertyDistributorEntityToDistributor(DistributorEntity distributor) {
        return new Distributor(distributor.getId(), distributor.getName());
    }

    @Override
    public DistributorEntity convertyDistributorToDistributorEntity(Distributor distributor) {
        return new DistributorEntity(distributor.getId(), distributor.getName());
    }


    @Override
    public Film convertyFilmEntityToFilm(FilmEntity filmEntity, List<Genre> genres, Director director, Distributor distributor) {
        return new Film(filmEntity.getId(), filmEntity.getTitle() ,genres, filmEntity.getOriginal_language(), director, filmEntity.getWriter(), filmEntity.getRelease_date(), filmEntity.getRuntime(), distributor, filmEntity.getProduction_co(), filmEntity.getAverage_rating_critic(), filmEntity.getAverage_rating_audience(), filmEntity.getUrl());
    }

    @Override
    public List<Film> convertyListFilmsEntityToListFilm(List<FilmEntity> filmEntities) {
        List<Film> films = new ArrayList<>();
        for (FilmEntity f : filmEntities ) {
            Film film = new Film(f.getId(), f.getTitle(), convertGenreEntityToGenre(f.getGenres()), f.getOriginal_language(), convertyDirectorEntityToDirector(f.getDirector()), f.getWriter(), f.getRelease_date(), f.getRuntime(), convertyDistributorEntityToDistributor(f.getDistributor()), f.getProduction_co(), f.getAverage_rating_critic(), f.getAverage_rating_audience(), f.getUrl());
            films.add(film);
        }
        return films;
    }

    @Override
    public FilmEntity convertyFilmToFilmEntity(Film f) {
        return new FilmEntity(f.getId(), f.getTitle(), convertGenreToGenreEntity(f.getGenres()), f.getOriginal_language(), convertyDirectorToDirectorEntity(f.getDirectorEntity()), f.getWriter(), f.getRelease_date(), f.getRuntime(), convertyDistributorToDistributorEntity(f.getDistributor()), f.getProduction_co(), f.getAverage_rating_critic(), f.getAverage_rating_audience(), f.getUrl());
    }


}
