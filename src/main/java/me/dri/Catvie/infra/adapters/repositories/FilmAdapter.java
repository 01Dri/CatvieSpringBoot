package me.dri.Catvie.infra.adapters.repositories;

import me.dri.Catvie.controllers.FilmController;
import me.dri.Catvie.domain.exceptions.InvalidGenre;
import me.dri.Catvie.domain.exceptions.NotFoundDirector;
import me.dri.Catvie.domain.exceptions.NotFoundFilm;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.models.entities.Genre;
import me.dri.Catvie.domain.ports.interfaces.film.MapperEntities;
import me.dri.Catvie.domain.ports.repositories.FilmRepositoryPort;
import me.dri.Catvie.infra.entities.FilmEntity;
import me.dri.Catvie.infra.entities.GenreEntity;
import me.dri.Catvie.infra.ports.DirectorRepositoryJPA;
import me.dri.Catvie.infra.ports.FilmRepositoryJPA;
import me.dri.Catvie.infra.ports.GenreRepositoryJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FilmAdapter implements FilmRepositoryPort {

    private final FilmRepositoryJPA filmRepositoryJPA;

    private final MapperEntities mapperEntities;

    private final GenreRepositoryJPA genreRepositoryPort;

    private final DirectorRepositoryJPA directorRepositoryJPA;

    private static final Logger logger = LoggerFactory.getLogger(FilmController.class);


    @Autowired
    public FilmAdapter(FilmRepositoryJPA filmRepositoryJPA, MapperEntities mapperEntities, GenreRepositoryJPA genreRepositoryPort, DirectorRepositoryJPA directorRepositoryJPA) {
        this.filmRepositoryJPA = filmRepositoryJPA;
        this.mapperEntities = mapperEntities;
        this.genreRepositoryPort = genreRepositoryPort;
        this.directorRepositoryJPA = directorRepositoryJPA;
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
    public List<Film> findAllFilmEntity() {
        logger.info("Endpoints films accessed");
        List<FilmEntity> films = this.filmRepositoryJPA.findAllFilms().orElseThrow(() -> new NotFoundFilm("Empty"));
        return this.mapperEntities.convertyListFilmsEntityToListFilm(films);
    }

    @Override
    public Film findByTitle(String title) {
        FilmEntity film = this.filmRepositoryJPA.findFilmByTitle(title).orElseThrow(() -> new NotFoundFilm("Film not found:  " + title));
        return this.mapperEntities.convertyFilmEntityToFilm(film);
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
        try {
            var genresEntity = film.getGenres().stream(
            ).map(genre -> this.genreRepositoryPort.findBygenreName(genre.getGenreName()).orElseThrow(()
                    -> new InvalidGenre("Genre not found"))).collect(Collectors.toSet());

            var directorEntity = this.directorRepositoryJPA.findByName(film.getDirectorEntity().getName()).orElseThrow(() -> new NotFoundDirector("Director not found"));
            FilmEntity filmEntity = this.mapperEntities.convertyFilmToFilmEntity(film);
            filmEntity.setGenres(genresEntity);
            filmEntity.setDirector(directorEntity);
            this.filmRepositoryJPA.save(filmEntity);

        } catch (NotFoundDirector | InvalidGenre e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void save(Film film) {
        FilmEntity filmEntity = this.mapperEntities.convertyFilmToFilmEntity(film);
        this.filmRepositoryJPA.save(filmEntity);
    }

    @Override
    public void delete(Film film) {
        FilmEntity filmEntity = this.mapperEntities.convertyFilmToFilmEntity(film);
        this.filmRepositoryJPA.delete(filmEntity);
    }

    @Override
    public Film update(Film film) {
        return null;
    }

}


