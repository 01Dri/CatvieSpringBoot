package me.dri.Catvie.unittest.mocks;

import me.dri.Catvie.domain.models.dto.director.DirectorResponseDTO;
import me.dri.Catvie.domain.models.entities.Director;
import me.dri.Catvie.domain.models.entities.Film;

import java.util.List;


public class MockDirector {

    private final MockFilm mockFilm;

    public MockDirector(MockFilm mockFilm) {
        this.mockFilm = mockFilm;
    }

    public DirectorResponseDTO mockDirectorDTO() {
        Film film1 = this.mockFilm.mockFilm();
        Film film2 = this.mockFilm.mockFilm();
        return new DirectorResponseDTO("Diego", List.of(film1, film2));
    }

    public Director mockDirector() {
        Film film1 = this.mockFilm.mockFilm();
        Film film2 = this.mockFilm.mockFilm();
        return new Director(1L, "Diego");
    }
}
