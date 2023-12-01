package me.dri.Catvie.domain.ports.interfaces.director;

import me.dri.Catvie.domain.models.dto.director.DirectorDTO;
import me.dri.Catvie.domain.models.dto.director.DirectorResponseDTO;

import java.util.List;
import java.util.Set;

public interface DirectorServicePort {

    DirectorDTO findById(Long id);

    Set<DirectorDTO> findAll();

    DirectorDTO findByName(String name);

    void create(DirectorDTO genre);

    void save(DirectorDTO genre);

    void delete(DirectorDTO genre);

    List<DirectorDTO> verifyExistingGenres(List<DirectorDTO> genreDTOS);


}


