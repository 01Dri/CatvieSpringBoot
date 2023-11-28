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


    @Override
    public Film findById(Long id) {
        FilmEntity film = this.filmRepositoryJPA.findFilmById(id).orElseThrow(() -> new NotFoundFilm("Film with this id: " + id + " does not exist!"));
        return this.mapperEntities.convertyFilmEntityToFilm(film);
    }

    @Override
    public List<Film> findAllFilmEntity() {
        logger.info("Endpoints films accessed");
        List<FilmEntity> films = this.filmRepositoryJPA.findAllFilms().orElseThrow(() -> new NotFoundFilm("Empty"));
        return this.mapperEntities.convertyListFilmsEntityToListFilm(films);
    }

    @Override
    public Film findByTitle(String title) {
        FilmEntity film = this.filmRepositoryJPA.findFilmByTitle(title).orElseThrow(() -> new NotFoundFilm("Film with this title: " + title + " Does not exist!"));
        return this.mapperEntities.convertyFilmEntityToFilm(film);
    }


    @Override
    public void create(Film film) {
        var genresEntity = this.getGenresToSetGenreEntity(film.getGenres());
        var directorEntity = this.directorRepositoryJPA.findByName(film.getDirectorEntity().getName()).orElseThrow(() -> new NotFoundDirector("Director not found"));
        FilmEntity filmEntity = this.mapperEntities.convertyFilmToFilmEntity(film);
        filmEntity.setGenres(genresEntity);
        filmEntity.setDirector(directorEntity);
        this.filmRepositoryJPA.save(filmEntity);
    }


    @Override
    public void delete(Film film) {
        FilmEntity filmEntity = this.mapperEntities.convertyFilmToFilmEntity(film);
        this.filmRepositoryJPA.delete(filmEntity);
    }

    @Override
    public Film update(Film film) {
        FilmEntity filmByService = this.mapperEntities.convertyFilmToFilmEntity(film);
        this.filmRepositoryJPA.save(filmByService);
        return film;
    }

    @Override
    public Long deleteById(Long id) {
        FilmEntity filmEntity = this.filmRepositoryJPA.findFilmById(id).orElseThrow(
                () -> new NotFoundFilm("The film was not found")); // Why this doesn't work
        this.filmRepositoryJPA.delete(filmEntity);
        return id;
    }

    public Set<GenreEntity> getGenresToSetGenreEntity(Set<Genre> genres) {
        return genres.stream().map(g -> this.genreRepositoryPort.findBygenreName(g.getGenreName()).orElseThrow(() -> new InvalidGenre("The genre was not found")))
                .collect(Collectors.toSet());
    }
}


