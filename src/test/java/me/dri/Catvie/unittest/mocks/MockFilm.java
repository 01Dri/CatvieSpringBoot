package me.dri.Catvie.unittest.mocks;

import me.dri.Catvie.domain.models.dto.director.DirectorDTO;
import me.dri.Catvie.domain.models.dto.film.FilmDTO;
import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;
import me.dri.Catvie.domain.models.dto.genre.GenreDTO;
import me.dri.Catvie.domain.models.dto.user.UserDTO;
import me.dri.Catvie.domain.models.dto.user.UserResponseDTO;
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

    private final UserResponseDTO userResponseDTO = this.mockUser.mockUserResponseDTO();
    private final DirectorDTO directorDTO = this.mockDirector.mockDirectorDTO();




    public MockFilm() {
    }
    public Film mockFilm() {
        return new Film(1L, "Evangelion", Set.of(this.genre), "english", this.director, "Diego",
                new Date(),150, "distributoteste", "diego", 5.4, 3.3, "teste", this.user);
    }

    public FilmDTO mockFilmDto() {
        return new FilmDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                "Diego", new Date(),150, "disney", "diego",
                 "teste");
    }



    public FilmEntity mockFilmEntity() {
        return new FilmEntity(1L, "Evangelion", Set.of(this.genreEntity), "english", this.directorEntity, "Diego",
                new Date(),150, "distributoteste", "diego", 6.0,
                8.5, "teste", this.userEntity);
    }

    public FilmDTO mockFilmDtoTitleEmpty() {
        return new FilmDTO("", Set.of(genreDTO), "english", this.directorDTO,
                "Diego", new Date(),150, "disney", "diego",
                 "teste");
    }

    public FilmDTO mockFilmDtoTitleNull() {
        return new FilmDTO(null, Set.of(this.genreDTO), "english", this.directorDTO,
                "Diego", new Date(),150, "disney", "diego",
                 "teste");
    }
    public FilmDTO mockFilmDtoLanguageEmpty() {
        return new FilmDTO("Evangelion", Set.of(this.genreDTO), "", this.directorDTO,
                "Diego", new Date(),150, "disney", "diego",
                 "teste");
    }

    public FilmDTO mockFilmDtoLanguageNull() {
        return new FilmDTO("Evangelion", Set.of(this.genreDTO), null, this.directorDTO,
                "Diego", new Date(),150, "disney",
                "diego",  "teste");
    }

    public FilmDTO mockFilmDtoWriterEmpty() {
        return new FilmDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                "", new Date(),150, "disney",
                "diego",  "teste");
    }

    public FilmDTO mockFilmDtoWriterNull() {
        return new FilmDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                null, new Date(),150, "disney",
                "diego",
                "teste");
    }

    public FilmDTO mockFilmDtoDistributorEmpty() {
        return new FilmDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                "Diego", new Date(),150, "", "diego",
                 "teste");
    }

    public FilmDTO mockFilmDtoDistributorNull() {
        return new FilmDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                "Diego", new Date(),150, null, "diego",
                 "teste");
    }

    public FilmDTO mockFilmDtoUrlNull() {
        return new FilmDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                "Diego", new Date(),150, "Diego", "Diego",
                 null);
    }

    public FilmDTO mockFilmDtoProductionEmpty() {
        return new FilmDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                "Diego", new Date(),150, "Diego", "",
                 "teste");
    }

    public FilmDTO mockFilmDtoProductionNully() {
        return new FilmDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                "Diego", new Date(),150, "Diego",
                null,  "teste");
    }



    public FilmDTO mockFilmDtoUrlEmpty() {
        return new FilmDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                "Diego", new Date(),150, "Diego", "Diego",
                 "");
    }

    public FilmDTO mockFilmDtoRuntimeNull() {
        return new FilmDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                "Diego", new Date(),null, "Diego",
                "Diego",  "teste");
    }


    public FilmDTO mockFilmDtoReleaseDateNull() {
        return new FilmDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                "Diego", null,189, "Diego",
                "Diego",  "teste");
    }


    public FilmResponseDTO mockFilmResponseDTO() {
        return new FilmResponseDTO(1L, "Evangelion",
        Set.of(genreDTO), "English",new Date(),
        120, "Teste", "Teste", "teste", 2.5, 3.4, "http", this.userResponseDTO);
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
