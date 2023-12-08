package me.dri.Catvie.unittest.mocks;

import me.dri.Catvie.domain.models.dto.director.DirectorRequestDTO;
import me.dri.Catvie.domain.models.dto.director.DirectorResponseDTO;
import me.dri.Catvie.domain.models.dto.film.FilmRequestDTO;
import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;
import me.dri.Catvie.domain.models.dto.genre.GenreRequestDTO;
import me.dri.Catvie.domain.models.dto.genre.GenreResponseDTO;
import me.dri.Catvie.domain.models.dto.user.UserDTO;
import me.dri.Catvie.domain.models.dto.user.UserResponseFilmRequestDTO;
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


    private final Genre genre = this.mockGenre.mockGenre();
    private final Director director = this.mockDirector.mockDirector();
    private final User user = this.mockUser.mockUser();


    private final UserEntity userEntity = this.mockUser.mockUserEntity();
    private final GenreEntity genreEntity = this.mockGenre.mockGenreEntity();
    private final DirectorEntity directorEntity= this.mockDirector.mockDirectorEntity();

    private final GenreRequestDTO genreDTO = this.mockGenre.mockGenreDTO();
    private final UserDTO userDTO = this.mockUser.mockUserDTO();

    private final UserResponseFilmRequestDTO userResponseDTO = this.mockUser.mockUserFilmResponseDTO();
    private final DirectorRequestDTO directorDTO = this.mockDirector.mockDirectorDTO();
    private final DirectorResponseDTO directorResponseDTO = this.mockDirector.mockDirectorResponseDTO();

    private final GenreResponseDTO mockGenreResponseDTO = this.mockGenre.mockGenreResponseDTO();


    public MockFilm() {
    }
    public Film mockFilm() {
        return new Film(1L, "Evangelion", Set.of(this.genre),
                "english", this.director, "Diego",
                new Date(),150, "distributoteste",
                "diego", 5.4, 3.3,
                "teste", this.user);
    }

    public FilmRequestDTO mockFilmRequestDTO() {
        return new FilmRequestDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                "Diego", new Date(),150, "disney", "diego",
                 "teste");
    }



    public FilmEntity mockFilmEntity() {
        return new FilmEntity(1L, "Evangelion", Set.of(this.genreEntity), "english", this.directorEntity, "Diego",
                new Date(),150, "distributoteste", "diego", 6.0,
                8.5, "teste", this.userEntity);
    }

    public FilmRequestDTO mockFilmRequestDTOTitleEmpty() {
        return new FilmRequestDTO("", Set.of(genreDTO), "english", this.directorDTO,
                "Diego", new Date(),150, "disney", "diego",
                 "teste");
    }

    public FilmRequestDTO mockFilmRequestDTOTitleNull() {
        return new FilmRequestDTO(null, Set.of(this.genreDTO), "english", this.directorDTO,
                "Diego", new Date(),150, "disney", "diego",
                 "teste");
    }
    public FilmRequestDTO mockFilmRequestDTOLanguageEmpty() {
        return new FilmRequestDTO("Evangelion", Set.of(this.genreDTO), "", this.directorDTO,
                "Diego", new Date(),150, "disney", "diego",
                 "teste");
    }

    public FilmRequestDTO mockFilmRequestDTOLanguageNull() {
        return new FilmRequestDTO("Evangelion", Set.of(this.genreDTO), null, this.directorDTO,
                "Diego", new Date(),150, "disney",
                "diego",  "teste");
    }

    public FilmRequestDTO mockFilmRequestDTOWriterEmpty() {
        return new FilmRequestDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                "", new Date(),150, "disney",
                "diego",  "teste");
    }

    public FilmRequestDTO mockFilmRequestDTOWriterNull() {
        return new FilmRequestDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                null, new Date(),150, "disney",
                "diego",
                "teste");
    }

    public FilmRequestDTO mockFilmRequestDTODistributorEmpty() {
        return new FilmRequestDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                "Diego", new Date(),150, "", "diego",
                 "teste");
    }

    public FilmRequestDTO mockFilmRequestDTODistributorNull() {
        return new FilmRequestDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                "Diego", new Date(),150, null, "diego",
                 "teste");
    }

    public FilmRequestDTO mockFilmRequestDTOUrlNull() {
        return new FilmRequestDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                "Diego", new Date(),150, "Diego", "Diego",
                 null);
    }

    public FilmRequestDTO mockFilmRequestDTOProductionEmpty() {
        return new FilmRequestDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                "Diego", new Date(),150, "Diego", "",
                 "teste");
    }

    public FilmRequestDTO mockFilmRequestDTOProductionNully() {
        return new FilmRequestDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                "Diego", new Date(),150, "Diego",
                null,  "teste");
    }



    public FilmRequestDTO mockFilmRequestDTOUrlEmpty() {
        return new FilmRequestDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                "Diego", new Date(),150, "Diego", "Diego",
                 "");
    }

    public FilmRequestDTO mockFilmRequestDTORuntimeNull() {
        return new FilmRequestDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                "Diego", new Date(),null, "Diego",
                "Diego",  "teste");
    }


    public FilmRequestDTO mockFilmRequestDTOReleaseDateNull() {
        return new FilmRequestDTO("Evangelion", Set.of(this.genreDTO), "english", this.directorDTO,
                "Diego", null,189, "Diego",
                "Diego",  "teste");
    }


        public List<FilmEntity> mockListFilmsEntity() {
        List<FilmEntity> filmEntities = new ArrayList<>();
        var film1 = this.mockFilmEntity();
        var film2 = this.mockFilmEntity();
        filmEntities.add(film1);
        filmEntities.add(film2);
        return filmEntities;
    }

    public FilmResponseDTO mockFilmResponseDTO() {
        return new FilmResponseDTO(1L, "Test Title",
                Set.of(this.mockGenreResponseDTO), "English" ,
                new Date(), 120, "Distributor", "EuDiego", "DiegoGames",
                7.6, 7.8, this.directorResponseDTO, "https:images", this.userResponseDTO);
    }

    public List<Film> mockListFilms() {
        List<Film> filmEntities = new ArrayList<>();
        var film1 = this.mockFilm();
        var film2 = this.mockFilm();
        filmEntities.add(film1);
        filmEntities.add(film2);
        return filmEntities;
    }
    public List<FilmResponseDTO> mockListFilmsResponseDTO() {
        List<FilmResponseDTO> filmEntities = new ArrayList<>();
        var film1 = this.mockFilmResponseDTO();
        var film2 = this.mockFilmResponseDTO();
        filmEntities.add(film1);
        filmEntities.add(film2);
        return filmEntities;
    }


}
