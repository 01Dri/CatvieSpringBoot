package me.dri.Catvie.domain.ports.interfaces.film;

import me.dri.Catvie.domain.models.dto.film.FilmDTO;
import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;

import java.util.List;

public interface FilmServicePort {

    FilmResponseDTO findById(Long id);
    List<FilmResponseDTO> findAll();
    FilmResponseDTO findByTitle(String title);
    FilmResponseDTO create(FilmDTO filmDto);
    void deleteById(Long id);
    FilmResponseDTO update(FilmDTO filmDTO);

}
