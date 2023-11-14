package me.dri.Catvie.unittest.mocks;

import me.dri.Catvie.domain.enums.Genres;
import me.dri.Catvie.domain.models.dto.film.FilmDTO;
import me.dri.Catvie.domain.models.dto.genre.GenreDTO;
import me.dri.Catvie.domain.models.entities.Director;
import me.dri.Catvie.domain.models.entities.Distributor;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.models.entities.Genre;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class MockFilm {


    public MockFilm() {
    }
    public Film mockFilm() {
        Genre genre = new Genre(1L , Genres.ACTION);
        Director director = new Director(1L, "Diego");
        Distributor distributor = new Distributor(1L, "Walt Disney");
        Film film = new Film(1L, "Evangelion", Set.of(genre), "english", director, "Diego", new Date(),150, distributor, "diego", 6.0, 8.5, "teste");
        return film;
    }

    public FilmDTO mockFilmDto() {
        GenreDTO genre = new GenreDTO(Genres.ACTION);
        Director directorEntity = new Director(1L, "Diego");
        Distributor distributor = new Distributor(1L, "Walt Disney");
        FilmDTO film = new FilmDTO("Evangelion", Set.of(genre), "english", directorEntity, "Diego", new Date(),150, distributor, "diego", 6.0, 8.5, "teste");
        return film;
    }

    public FilmDTO mockFilmDtoWithNullValue() {
        GenreDTO genre = new GenreDTO(Genres.ACTION);
        Director directorEntity = new Director(1L, "Diego");
        Distributor distributor = new Distributor(1L, "Walt Disney");
        FilmDTO film = new FilmDTO("", Set.of(genre), "english", directorEntity, "Diego", new Date(),150, distributor, "diego", 6.0, 8.5, "teste");
        return film;
    }
}
