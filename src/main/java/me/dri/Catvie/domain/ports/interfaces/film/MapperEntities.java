package me.dri.Catvie.domain.ports.interfaces.film;

import me.dri.Catvie.domain.models.entities.Director;
import me.dri.Catvie.domain.models.entities.Distributor;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.models.entities.Genre;
import me.dri.Catvie.infra.entities.DirectorEntity;
import me.dri.Catvie.infra.entities.DistributorEntity;
import me.dri.Catvie.infra.entities.FilmEntity;
import me.dri.Catvie.infra.entities.GenreEntity;

import java.util.List;
import java.util.Set;

public interface MapperEntities {

    Set<Genre> convertGenreEntityToGenre(Set<GenreEntity> genreEntities);

    Set<GenreEntity> convertGenreToGenreEntity(Set<Genre> genreEntities);

    Director convertyDirectorEntityToDirector(DirectorEntity director);

    DirectorEntity convertyDirectorToDirectorEntity(Director director);

    Distributor convertyDistributorEntityToDistributor(DistributorEntity distributor);

    DistributorEntity convertyDistributorToDistributorEntity(Distributor distributor);

    Film convertyFilmEntityToFilm(FilmEntity filmEntity, Set<Genre> genres, Director director, Distributor distributor);

    List<Film> convertyListFilmsEntityToListFilm(List<FilmEntity> filmEntities);

    FilmEntity convertyFilmToFilmEntity(Film film);



}
