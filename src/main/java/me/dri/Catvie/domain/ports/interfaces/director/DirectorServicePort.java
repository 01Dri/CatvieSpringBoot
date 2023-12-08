package me.dri.Catvie.domain.ports.interfaces.director;

import me.dri.Catvie.domain.models.dto.director.DirectorRequestDTO;
import me.dri.Catvie.domain.models.dto.director.DirectorResponseDTO;

import java.util.List;
import java.util.Set;

public interface DirectorServicePort {

    DirectorResponseDTO findById(Long id);

    Set<DirectorResponseDTO> findAll();

    DirectorResponseDTO findByName(String name);

    DirectorResponseDTO create(DirectorRequestDTO genre);

    DirectorResponseDTO save(DirectorRequestDTO genre);

    DirectorResponseDTO delete(DirectorRequestDTO genre);

    List<DirectorResponseDTO> verifyExistingGenres(List<DirectorRequestDTO> genreDTOS);


}


