package me.dri.Catvie.domain.ports.interfaces.genre;

import me.dri.Catvie.domain.models.dto.genre.GenreRequestDTO;
import me.dri.Catvie.domain.models.dto.genre.GenreResponseDTO;
import me.dri.Catvie.domain.models.core.Genre;

import java.util.Set;

public interface GenreServicesPort {

    GenreResponseDTO findById(Long id);

    Set<GenreResponseDTO> findAll();

    GenreResponseDTO findByName(String title);

    GenreResponseDTO create(GenreRequestDTO genre);

    GenreResponseDTO save(GenreRequestDTO genre);

    GenreResponseDTO delete(GenreRequestDTO genre);

    Set<Genre> verifyExistingGenres(Set<GenreRequestDTO> genreDTOS);


}
