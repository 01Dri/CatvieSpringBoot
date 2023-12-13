package me.dri.Catvie.unittest.mocks;

import me.dri.Catvie.domain.models.dto.director.DirectorRequestDTO;
import me.dri.Catvie.domain.models.dto.director.DirectorResponseDTO;
import me.dri.Catvie.domain.models.core.Director;
import me.dri.Catvie.infra.entities.DirectorEntity;


public class MockDirector {

    private final MockFilm mockFilm;

    public MockDirector(MockFilm mockFilm) {
        this.mockFilm = mockFilm;
    }

    public DirectorRequestDTO mockDirectorDTO() {
        return new DirectorRequestDTO("Diego");
    }

    public DirectorResponseDTO mockDirectorResponseDTO() {
        return new DirectorResponseDTO(1L, "Diego");
    }

    public Director mockDirector() {
        return new Director(1L, "Diego");
    }

    public DirectorEntity mockDirectorEntity() {
        return new DirectorEntity(1L , "Diego");
    }

}
