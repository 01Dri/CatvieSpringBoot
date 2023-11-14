package me.dri.Catvie.infra.adapters.repositories;

import me.dri.Catvie.domain.models.entities.Director;
import me.dri.Catvie.domain.models.entities.Distributor;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.models.entities.Genre;
import me.dri.Catvie.domain.ports.repositories.FilmRepositoryPort;
import me.dri.Catvie.infra.entities.FilmEntity;
import me.dri.Catvie.domain.ports.interfaces.film.MapperEntities;
import me.dri.Catvie.infra.ports.FilmRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilmAdapter implements FilmRepositoryPort {

    private final FilmRepositoryJPA filmRepositoryJPA;

    private final MapperEntities mapperEntities;
    
    @Autowired
    public FilmAdapter(FilmRepositoryJPA filmRepositoryJPA, MapperEntities mapperEntities) {
        this.filmRepositoryJPA = filmRepositoryJPA;
        this.mapperEntities = mapperEntities;
    }

//    @Override
//    public Film findById(Long id) {
//
//        FilmEntity film = this.filmRepositoryJPA.findFilmById(id);
//        List<Genre> genres = this.mapperEntities.convertGenreEntityToGenre(film.getGenres());
//        Director director = this.mapperEntities.convertyDirectorEntityToDirector(film.getDirector());
//        Distributor distributor = this.mapperEntities.convertyDistributorEntityToDistributor(film.getDistributor());
//
//        return this.mapperEntities.convertyFilmEntityToFilm(film, genres, director, distributor);
//    }

    @Override
    public Film findById(Long id) {
        return null;
    }

    @Override
    public List<Film> findAll() {
        List<FilmEntity> films = this.filmRepositoryJPA.findAll();
        return this.mapperEntities.convertyListFilmsEntityToListFilm(films);
    }

    @Override
    public Film findByTitle(String title) {
        return null;
    }

//    @Override
////    public Film findByTitle(String title) {
////        FilmEntity film = this.filmRepositoryJPA.findFilmByTitle(title);
////      //  List<Genre> genres = this.mapperEntities.convertGenreEntityToGenre(film.getGenres());
////        Director director = this.mapperEntities.convertyDirectorEntityToDirector(film.getDirector());
////        Distributor distributor = this.mapperEntities.convertyDistributorEntityToDistributor(film.getDistributor());
////        return this.mapperEntities.convertyFilmEntityToFilm(film, genres, director, distributor);
////    }

    @Override
    public void create(Film film) {
        FilmEntity filmEntity = this.mapperEntities.convertyFilmToFilmEntity(film);
        this.filmRepositoryJPA.save(filmEntity);
    }

    @Override
    public void save(Film film) {
        FilmEntity filmEntity = this.mapperEntities.convertyFilmToFilmEntity(film);
        this.filmRepositoryJPA.delete(filmEntity);
    }

    @Override
    public void delete(Film film) {
        FilmEntity filmEntity = this.mapperEntities.convertyFilmToFilmEntity(film);
        this.filmRepositoryJPA.delete(filmEntity);
    }

}


