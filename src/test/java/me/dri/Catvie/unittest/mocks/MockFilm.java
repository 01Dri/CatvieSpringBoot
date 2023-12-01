package me.dri.Catvie.unittest.mocks;

import me.dri.Catvie.domain.models.dto.director.DirectorDTO;
import me.dri.Catvie.domain.models.dto.film.FilmDTO;
import me.dri.Catvie.domain.models.dto.genre.GenreDTO;
import me.dri.Catvie.domain.models.dto.user.UserDTO;
import me.dri.Catvie.domain.models.entities.Director;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.models.entities.Genre;
import me.dri.Catvie.domain.models.entities.User;
import me.dri.Catvie.infra.entities.DirectorEntity;
import me.dri.Catvie.infra.entities.FilmEntity;
import me.dri.Catvie.infra.entities.GenreEntity;
import me.dri.Catvie.infra.entities.UserEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class MockFilm {

    private final MockGenre mockGenre = new MockGenre();
    private final MockDirector mockDirector = new MockDirector(this);
    private final MockUser mockUser = new MockUser();


    private final Genre genre= this.mockGenre.mockGenre();
    private final Director director = this.mockDirector.mockDirector();
    private final User user = this.mockUser.mockUser();


    private final UserEntity userEntity = this.mockUser.mockUserEntity();
    private final GenreEntity genreEntity = this.mockGenre.mockGenreEntity();
    private final DirectorEntity directorEntity= this.mockDirector.mockDirectorEntity();

    private final GenreDTO genreDTO = this.mockGenre.mockGenreDTO();
    private final UserDTO userDTO = this.mockUser.mockUserDTO();
    private final DirectorDTO directorDTO = this.mockDirector.mockDirectorDTO();




    public MockFilm() {
    }
    public Film mockFilm() {
        return new Film(1L, "Evangelion", Set.of(this.genre), "english", this.director, "Diego",
                new Date(),150, "distributoteste", "diego", 6.0, 8.5, "teste", this.user);
    }

    public FilmDTO mockFilmDto() {
        return new FilmDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                "Diego", new Date(),150, "disney", "diego",
                6.0, 8.5, "teste", this.userDTO);
    }



    public FilmEntity mockFilmEntity() {
        return new FilmEntity(1L, "Evangelion", Set.of(this.genreEntity), "english", this.directorEntity, "Diego",
                new Date(),150, "distributoteste", "diego", 6.0,
                8.5, "teste", this.userEntity);
    }

    public FilmDTO mockFilmDtoTitleEmpty() {
        return new FilmDTO("", Set.of(genreDTO), "english", this.directorDTO,
                "Diego", new Date(),150, "disney", "diego",
                6.0, 8.5, "teste", this.userDTO);
    }

    public FilmDTO mockFilmDtoTitleNull() {
        return new FilmDTO(null, Set.of(this.genreDTO), "english", this.directorDTO,
                "Diego", new Date(),150, "disney", "diego",
                6.0, 8.5, "teste", this.userDTO);
    }
    public FilmDTO mockFilmDtoLanguageEmpty() {
        return new FilmDTO("Evangelion", Set.of(this.genreDTO), "", this.directorDTO,
                "Diego", new Date(),150, "disney", "diego",
                6.0, 8.5, "teste", this.userDTO);
    }

    public FilmDTO mockFilmDtoLanguageNull() {
        return new FilmDTO("Evangelion", Set.of(this.genreDTO), null, this.directorDTO,
                "Diego", new Date(),150, "disney",
                "diego", 6.0, 8.5, "teste", this.userDTO);
    }

    public FilmDTO mockFilmDtoWriterEmpty() {
        return new FilmDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                "", new Date(),150, "disney",
                "diego", 6.0, 8.5, "teste", this.userDTO);
    }

    public FilmDTO mockFilmDtoWriterNull() {
        return new FilmDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                null, new Date(),150, "disney",
                "diego", 6.0,
                8.5, "teste", this.userDTO);
    }

    public FilmDTO mockFilmDtoDistributorEmpty() {
        return new FilmDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                "Diego", new Date(),150, "", "diego",
                6.0, 8.5, "teste", this.userDTO);
    }

    public FilmDTO mockFilmDtoDistributorNull() {
        return new FilmDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                "Diego", new Date(),150, null, "diego",
                6.0, 8.5, "teste", this.userDTO);
    }

    public FilmDTO mockFilmDtoUrlNull() {
        return new FilmDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                "Diego", new Date(),150, "Diego", "Diego",
                6.0, 8.5, null, this.userDTO);
    }

    public FilmDTO mockFilmDtoProductionEmpty() {
        return new FilmDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                "Diego", new Date(),150, "Diego", "",
                6.0, 8.5, "teste", this.userDTO);
    }

    public FilmDTO mockFilmDtoProductionNully() {
        return new FilmDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                "Diego", new Date(),150, "Diego",
                null, 6.0, 8.5, "teste", this.userDTO);
    }



    public FilmDTO mockFilmDtoUrlEmpty() {
        return new FilmDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                "Diego", new Date(),150, "Diego", "Diego",
                6.0, 8.5, "", this.userDTO);
    }

    public FilmDTO mockFilmDtoRuntimeNull() {
        return new FilmDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                "Diego", new Date(),null, "Diego",
                "Diego", 6.0, 8.5, "teste", this.userDTO);
    }


    public FilmDTO mockFilmDtoReleaseDateNull() {
        return new FilmDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                "Diego", null,189, "Diego",
                "Diego", 6.0, 8.5, "teste", this.userDTO);
    }

    public FilmDTO mockFilmDtoAverageCriticNull() {
        return new FilmDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                "Diego", new Date(),189, "Diego",
                "Diego", null, 8.5, "teste", this.userDTO);
    }

    public FilmDTO mockFilmDtoAverageAudienceNull() {
        return new FilmDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                "Diego", new Date(),189, "Diego",
                "Diego", 6.0, null, "teste", this.userDTO);
    }

    public List<FilmEntity> mockListFilmsEntity() {
        List<FilmEntity> filmEntities = new ArrayList<>();
        var film1 = this.mockFilmEntity();
        var film2 = this.mockFilmEntity();
        filmEntities.add(film1);
        filmEntities.add(film2);
        return filmEntities;
    }

    public List<Film> mockListFilms() {
        List<Film> filmEntities = new ArrayList<>();
        var film1 = this.mockFilm();
        var film2 = this.mockFilm();
        filmEntities.add(film1);
        filmEntities.add(film2);
        return filmEntities;
    }
}
