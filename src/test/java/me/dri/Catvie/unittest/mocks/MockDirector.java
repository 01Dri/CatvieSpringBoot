package me.dri.Catvie.unittest.mocks;

import me.dri.Catvie.domain.models.dto.director.DirectorRequestDTO;
import me.dri.Catvie.domain.models.dto.film.FilmRequestDTO;
import me.dri.Catvie.domain.models.entities.Director;
import me.dri.Catvie.infra.entities.DirectorEntity;

import java.util.List;


public class MockDirector {

    private final MockFilm mockFilm;

    public MockDirector(MockFilm mockFilm) {
        this.mockFilm = mockFilm;
    }

    public DirectorRequestDTO mockDirectorDTO() {
        FilmRequestDTO film1 = this.mockFilm.mockFilmRequestDTO();
        FilmRequestDTO film2 = this.mockFilm.mockFilmRequestDTO();
        return new DirectorRequestDTO("Diego", List.of(film1, film2));
    }

    public Director mockDirector() {
        return new Director(1L, "Diego");
    }

    public DirectorEntity mockDirectorEntity() {
        return new DirectorEntity(1L , "Diego");
    }

}
