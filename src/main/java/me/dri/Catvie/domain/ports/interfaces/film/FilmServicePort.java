package me.dri.Catvie.domain.ports.interfaces.film;

import me.dri.Catvie.domain.models.dto.film.FilmDTO;
import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;

import java.util.List;

public interface FilmServicePort {

    FilmDTO findById(Long id);
    List<FilmDTO> findAll();
    FilmDTO findByTitle(String title);
    FilmResponseDTO create(FilmDTO filmDto);
    void save(FilmDTO film);
    void deleteById(Long id);

}
