package me.dri.Catvie.unittest.mocks;

import me.dri.Catvie.domain.models.dto.director.DirectorDTO;
import me.dri.Catvie.domain.models.dto.director.DirectorResponseDTO;
import me.dri.Catvie.domain.models.entities.Director;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.infra.entities.DirectorEntity;

import java.util.List;


public class MockDirector {

    private final MockFilm mockFilm;

    public MockDirector(MockFilm mockFilm) {
        this.mockFilm = mockFilm;
    }

    public DirectorDTO mockDirectorDTO() {
        Film film1 = this.mockFilm.mockFilm();
        Film film2 = this.mockFilm.mockFilm();
        return new DirectorDTO("Diego", List.of(film1, film2));
    }

    public Director mockDirector() {
        Film film1 = this.mockFilm.mockFilm();
        Film film2 = this.mockFilm.mockFilm();
        return new Director(1L, "Diego");
    }

    public DirectorEntity mockDirectorEntity() {
        return new DirectorEntity(1L , "Diego");
    }

}
