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
        return new Film(1L, "Evangelion", Set.of(genre), "english", director, "Diego", new Date(),150, "distributoteste", "diego", 6.0, 8.5, "teste");
    }

    public FilmDTO mockFilmDto() {
        GenreDTO genre = new GenreDTO(Genres.ACTION);
        return new FilmDTO("Evangelion", Set.of(genre), "english", new DirectorCreateDTO("Diego"), "Diego", new Date(),150, "disney", "diego", 6.0, 8.5, "teste");
    }



    public FilmEntity mockFilmEntity() {
        GenreEntity genre = new GenreEntity(1L, Genres.ACTION);
        DirectorEntity director = new DirectorEntity(1L , "Diego");
        return new FilmEntity(1L, "Evangelion", Set.of(genre), "english", director, "Diego", new Date(),150, "distributoteste", "diego", 6.0, 8.5, "teste");
    }

    public FilmDTO mockFilmDtoTitleEmpty() {
        GenreDTO genre = new GenreDTO(Genres.ACTION);
        return new FilmDTO("", Set.of(genre), "english", new DirectorCreateDTO("Diego"), "Diego", new Date(),150, "disney", "diego", 6.0, 8.5, "teste");
    }

    public FilmDTO mockFilmDtoTitleNull() {
        GenreDTO genre = new GenreDTO(Genres.ACTION);
        return new FilmDTO(null, Set.of(genre), "english", new DirectorCreateDTO("Diego"), "Diego", new Date(),150, "disney", "diego", 6.0, 8.5, "teste");
    }
    public FilmDTO mockFilmDtoLanguageEmpty() {
        GenreDTO genre = new GenreDTO(Genres.ACTION);
        return new FilmDTO("Evangelion", Set.of(genre), "", new DirectorCreateDTO("Diego"), "Diego", new Date(),150, "disney", "diego", 6.0, 8.5, "teste");
    }

    public FilmDTO mockFilmDtoLanguageNull() {
        GenreDTO genre = new GenreDTO(Genres.ACTION);
        return new FilmDTO("Evangelion", Set.of(genre), null, new DirectorCreateDTO("Diego"), "Diego", new Date(),150, "disney", "diego", 6.0, 8.5, "teste");
    }

    public FilmDTO mockFilmDtoWriterEmpty() {
        GenreDTO genre = new GenreDTO(Genres.ACTION);
        return new FilmDTO("Evangelion", Set.of(genre), "english", new DirectorCreateDTO("Diego"), "", new Date(),150, "disney", "diego", 6.0, 8.5, "teste");
    }

    public FilmDTO mockFilmDtoWriterNull() {
        GenreDTO genre = new GenreDTO(Genres.ACTION);
        return new FilmDTO("Evangelion", Set.of(genre), "english", new DirectorCreateDTO("Diego"), null, new Date(),150, "disney", "diego", 6.0, 8.5, "teste");
    }

    public FilmDTO mockFilmDtoDistributorEmpty() {
        GenreDTO genre = new GenreDTO(Genres.ACTION);
        return new FilmDTO("Evangelion", Set.of(genre), "english", new DirectorCreateDTO("Diego"), "Diego", new Date(),150, "", "diego", 6.0, 8.5, "teste");
    }

    public FilmDTO mockFilmDtoDistributorNull() {
        GenreDTO genre = new GenreDTO(Genres.ACTION);
        return new FilmDTO("Evangelion", Set.of(genre), "english", new DirectorCreateDTO("Diego"), "Diego", new Date(),150, null, "diego", 6.0, 8.5, "teste");
    }

    public FilmDTO mockFilmDtoUrlNull() {
        GenreDTO genre = new GenreDTO(Genres.ACTION);
        return new FilmDTO("Evangelion", Set.of(genre), "english", new DirectorCreateDTO("Diego"), "Diego", new Date(),150, "Diego", "Diego", 6.0, 8.5, null);
    }

    public FilmDTO mockFilmDtoProductionEmpty() {
        GenreDTO genre = new GenreDTO(Genres.ACTION);
        return new FilmDTO("Evangelion", Set.of(genre), "english", new DirectorCreateDTO("Diego"), "Diego", new Date(),150, "Diego", "", 6.0, 8.5, "teste");
    }

    public FilmDTO mockFilmDtoProductionNully() {
        GenreDTO genre = new GenreDTO(Genres.ACTION);
        return new FilmDTO("Evangelion", Set.of(genre), "english", new DirectorCreateDTO("Diego"), "Diego", new Date(),150, "Diego", null, 6.0, 8.5, "teste");
    }

    public FilmDTO mockFilmDtoUrlEmpty() {
        GenreDTO genre = new GenreDTO(Genres.ACTION);
        return new FilmDTO("Evangelion", Set.of(genre), "english", new DirectorCreateDTO("Diego"), "Diego", new Date(),150, "Diego", "Diego", 6.0, 8.5, "");
    }

    public FilmDTO mockFilmDtoRuntimeNull() {
        GenreDTO genre = new GenreDTO(Genres.ACTION);
        return new FilmDTO("Evangelion", Set.of(genre), "english", new DirectorCreateDTO("Diego"), "Diego", new Date(),null, "Diego", "Diego", 6.0, 8.5, "teste");
    }


    public FilmDTO mockFilmDtoReleaseDateNull() {
        GenreDTO genre = new GenreDTO(Genres.ACTION);
        return new FilmDTO("Evangelion", Set.of(genre), "english", new DirectorCreateDTO("Diego"), "Diego", null,189, "Diego", "Diego", 6.0, 8.5, "teste");
    }

    public FilmDTO mockFilmDtoAverageCriticNull() {
        GenreDTO genre = new GenreDTO(Genres.ACTION);
        return new FilmDTO("Evangelion", Set.of(genre), "english", new DirectorCreateDTO("Diego"), "Diego", new Date(),189, "Diego", "Diego", null, 8.5, "teste");
    }

    public FilmDTO mockFilmDtoAverageAudienceNull() {
        GenreDTO genre = new GenreDTO(Genres.ACTION);
        return new FilmDTO("Evangelion", Set.of(genre), "english", new DirectorCreateDTO("Diego"), "Diego", new Date(),189, "Diego", "Diego", 6.0, null, "teste");
    }
}
