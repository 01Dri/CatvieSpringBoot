package me.dri.Catvie.infra.adapters;

import me.dri.Catvie.controllers.FilmController;
import me.dri.Catvie.domain.exceptions.InvalidGenre;
import me.dri.Catvie.domain.exceptions.NotFoundDirector;
import me.dri.Catvie.domain.exceptions.film.NotFoundFilm;
import me.dri.Catvie.domain.exceptions.user.NotFoundUser;
import me.dri.Catvie.domain.models.core.Film;
import me.dri.Catvie.domain.models.core.Genre;
import me.dri.Catvie.domain.ports.repositories.FilmRepositoryPort;
import me.dri.Catvie.infra.entities.FilmEntity;
import me.dri.Catvie.infra.entities.GenreEntity;
import me.dri.Catvie.infra.entities.UserEntity;
import me.dri.Catvie.infra.repositoriesjpa.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class FilmAdapter implements FilmRepositoryPort {

    private final FilmRepositoryJPA filmRepositoryJPA;

    private final GenreRepositoryJPA genreRepositoryPort;

    private final DirectorRepositoryJPA directorRepositoryJPA;

    private final NotesAudiencesRepositoryJPA audiencesRepositoryJPA;

    private final UserRepositoryJPA userRepositoryJPA;

    private final ModelMapper mapper; // This is the mapper for to convert my entities
    private static final Logger logger = LoggerFactory.getLogger(FilmController.class);


    @Autowired
    public FilmAdapter(FilmRepositoryJPA filmRepositoryJPA, GenreRepositoryJPA genreRepositoryPort, DirectorRepositoryJPA directorRepositoryJPA, NotesAudiencesRepositoryJPA audiencesRepositoryJPA, UserRepositoryJPA userRepositoryJPA, ModelMapper mapper) {
        this.filmRepositoryJPA = filmRepositoryJPA;
        this.genreRepositoryPort = genreRepositoryPort;
        this.directorRepositoryJPA = directorRepositoryJPA;
        this.audiencesRepositoryJPA = audiencesRepositoryJPA;
        this.userRepositoryJPA = userRepositoryJPA;
        this.mapper = mapper;
    }



    @Override
    public Film findById(Long id) {
        FilmEntity filmById = this.filmRepositoryJPA.findFilmById(id).orElseThrow(() -> new NotFoundFilm("Film with this id: " + id + " does not exist!"));
                        return this.mapper.map(filmById, Film.class);
    }

    @Override
    public List<Film> findAllFilmEntity() {
        logger.info("Endpoints films accessed");
        List<FilmEntity> filmsAll = this.filmRepositoryJPA.findAllFilms().orElseThrow(() -> new NotFoundFilm("Empty"));
        for (FilmEntity film : filmsAll) {
            Link link = linkTo(FilmController.class).slash("/byId/" + film.getId()).withSelfRel();
            film.add(link);
        }
        return filmsAll.stream().map(f -> this.mapper.map(f, Film.class)).toList();
    }

    @Override
    public Film findByTitle(String title) {
        FilmEntity filmByTitle = this.filmRepositoryJPA.findFilmByTitle(title).orElseThrow(() -> new NotFoundFilm("Film with this title: " + title + " Does not exist!"));
        return this.mapper.map(filmByTitle, Film.class);
    }


    @Override
    public Film create(Film film, String subjectEmail) {
        var genresEntityByDB = this.getGenresAndConvertToSetGenreEntity(film.getGenres());
        var directorEntityByDB = this.directorRepositoryJPA.findByName(film.getDirector().getName()).orElseThrow(() -> new NotFoundDirector("Director not found"));
        var userEntityByDB = this.userRepositoryJPA.findByEmail(subjectEmail).orElseThrow(() -> new NotFoundUser("User not found"));
        FilmEntity filmConvertedToFilmEntity = this.mapper.map(film, FilmEntity.class);
        filmConvertedToFilmEntity.setGenres(genresEntityByDB);
        filmConvertedToFilmEntity.setDirector(directorEntityByDB);
        filmConvertedToFilmEntity.setUser((UserEntity) userEntityByDB);
        this.filmRepositoryJPA.save(filmConvertedToFilmEntity);
        return this.mapper.map(filmConvertedToFilmEntity, Film.class);
    }


    @Override
    public void delete(Film film) {
        FilmEntity filmConvertedToFilmEntity = this.mapper.map(film, FilmEntity.class);
        this.filmRepositoryJPA.delete(filmConvertedToFilmEntity); //Deleting the user of DB
    }

    @Override
    public Film update(Film film) {
        FilmEntity filmByServiceConvertedToFilmEntity = this.mapper.map(film, FilmEntity.class);
        this.filmRepositoryJPA.save(filmByServiceConvertedToFilmEntity);
        return this.mapper.map(filmByServiceConvertedToFilmEntity, Film.class);
    }

    @Override
    public Long deleteById(Long id) {
        FilmEntity filmEntityById = this.filmRepositoryJPA.findFilmById(id).orElseThrow(
                () -> new NotFoundFilm("The film was not found")); // Why this doesn't work
        this.audiencesRepositoryJPA.deleteByFilmId(filmEntityById.getId());
        this.filmRepositoryJPA.delete(filmEntityById);
        return id;
    }

    private Set<GenreEntity> getGenresAndConvertToSetGenreEntity(Set<Genre> genres) {
        return genres.stream().map(g ->
                        this.genreRepositoryPort.findBygenreName(g.getGenreName()).orElseThrow(()
                                -> new InvalidGenre("The genre was not found"))) // This function is used for find each genre of Set and returns a Set Of GenreEntity from DB
                .collect(Collectors.toSet());
    }
}


