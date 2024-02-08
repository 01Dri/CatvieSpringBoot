package me.dri.Catvie.domain.ports.interfaces.genre;

import me.dri.Catvie.domain.models.dto.genre.GenreRequestDTO;
import me.dri.Catvie.domain.models.dto.genre.GenreResponseDTO;
import me.dri.Catvie.domain.models.core.Genre;

import java.util.Set;

public interface GenreServicesPort {


    Set<GenreResponseDTO> findAll();


    Set<Genre> getAllGenresByGenreDTO(Set<GenreRequestDTO> genreDTOS);


}
