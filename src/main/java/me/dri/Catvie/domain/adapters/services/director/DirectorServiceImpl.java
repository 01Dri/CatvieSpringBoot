package me.dri.Catvie.domain.adapters.services.director;

import me.dri.Catvie.domain.exceptions.NotFoundDirector;
import me.dri.Catvie.domain.models.dto.director.DirectorRequestDTO;
import me.dri.Catvie.domain.models.dto.director.DirectorResponseDTO;
import me.dri.Catvie.domain.models.core.Director;
import me.dri.Catvie.domain.ports.interfaces.director.DirectorServicePort;
import me.dri.Catvie.domain.ports.repositories.DirectorRepositoryPort;

import java.util.List;
import java.util.Set;

public class DirectorServiceImpl  implements DirectorServicePort {

    private final DirectorRepositoryPort repositoryPort;

    public DirectorServiceImpl(DirectorRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public DirectorResponseDTO findByName(String title) {
        Director director = this.repositoryPort.findByName(title);
        if (director == null) {
            throw new NotFoundDirector("Director with name " + title + " Not exists");
        }
        return new DirectorResponseDTO(director.getId(), director.getName());
    }
}
