package me.dri.Catvie.unittest.mocks;

import me.dri.Catvie.domain.enums.Genres;
import me.dri.Catvie.domain.models.dto.director.DirectorCreateDTO;
import me.dri.Catvie.domain.models.dto.film.FilmDTO;
import me.dri.Catvie.domain.models.dto.genre.GenreDTO;
import me.dri.Catvie.domain.models.entities.Director;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.models.entities.Genre;
import me.dri.Catvie.infra.entities.DirectorEntity;
import me.dri.Catvie.infra.entities.FilmEntity;
import me.dri.Catvie.infra.entities.GenreEntity;

import java.util.Date;
import java.util.Set;

public class MockFilm {


    public MockFilm() {
    }
    public Film mockFilm() {
        Genre genre = new Genre(1L , Genres.ACTION);
        Director director = new Director(1L, "Diego");
        Film film = new Film(1L, "Evangelion", Set.of(genre), "english", director, "Diego", new Date(),150, "distributoteste", "diego", 6.0, 8.5, "teste");
        return film;
    }

    public FilmDTO mockFilmDto() {
        GenreDTO genre = new GenreDTO(Genres.ACTION);

        FilmDTO film = new FilmDTO("Evangelion", Set.of(genre), "english", new DirectorCreateDTO("Diego"), "Diego", new Date(),150, "disney", "diego", 6.0, 8.5, "teste");
        return film;
    }

    public FilmDTO mockFilmDtoWithNullValue() {
        GenreDTO genre = new GenreDTO(Genres.ACTION);
        FilmDTO film = new FilmDTO("", Set.of(genre), "english", new DirectorCreateDTO("diego"), "Diego", new Date(),150, "disney", "diego", 6.0, 8.5, "teste");
        return film;
    }

    public FilmEntity mockFilmEntity() {
        GenreEntity genre = new GenreEntity(1L, Genres.ACTION);
        DirectorEntity director = new DirectorEntity(1L , "Diego");
        return new FilmEntity(1L, "Evangelion", Set.of(genre), "english", director, "Diego", new Date(),150, "distributoteste", "diego", 6.0, 8.5, "teste");
    }
}
