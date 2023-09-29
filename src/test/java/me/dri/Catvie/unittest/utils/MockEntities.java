package me.dri.Catvie.unittest.utils;

import me.dri.Catvie.entity.enums.Genres;
import me.dri.Catvie.entity.models.Director;
import me.dri.Catvie.entity.models.Distributor;
import me.dri.Catvie.entity.models.Film;
import me.dri.Catvie.entity.models.Genre;
import me.dri.Catvie.entity.models.dto.FilmDto;

import java.util.Date;
import java.util.List;

public class MockEntities {


    public MockEntities() {
    }
    public Film mockFilm() {
        Genre genre = new Genre(1L , Genres.ACTION);
        Director director = new Director(1L, "Diego");
        Distributor distributor = new Distributor(1L, "Walt Disney");
        Film film = new Film(1L, "Evangelion", List.of(genre), "english", director, "Diego", new Date(),150, distributor, "diego", 6.0, 8.5);
        return film;
    }

    public FilmDto mockFilmDto() {
        Genre genre = new Genre(1L , Genres.ACTION);
        Director director = new Director(1L, "Diego");
        Distributor distributor = new Distributor(1L, "Walt Disney");
        FilmDto film = new FilmDto("Evangelion", List.of(genre), "english", director, "Diego", new Date(),150, distributor, "diego", 6.0, 8.5);
        return film;
    }

    public FilmDto mockFilmDtoWithNullValue() {
        Genre genre = new Genre(1L , Genres.ACTION);
        Director director = new Director(1L, "Diego");
        Distributor distributor = new Distributor(1L, "Walt Disney");
        FilmDto film = new FilmDto("", List.of(genre), "english", director, "Diego", new Date(),150, distributor, "diego", 6.0, 8.5);
        return film;
    }
}
