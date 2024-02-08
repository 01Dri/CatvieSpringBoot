package me.dri.Catvie.domain.ports.interfaces.director;

import me.dri.Catvie.domain.models.dto.director.DirectorResponseDTO;

public interface DirectorServicePort {
    DirectorResponseDTO findByName(String name);

}

