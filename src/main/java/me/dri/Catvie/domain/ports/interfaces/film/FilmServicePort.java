package me.dri.Catvie.domain.ports.interfaces.film;

import me.dri.Catvie.domain.models.dto.film.FilmRequestDTO;
import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;

import java.util.List;

public interface FilmServicePort {

    FilmResponseDTO findById(Long id);
    List<FilmResponseDTO> findAll();
    FilmResponseDTO findByTitle(String title);
    FilmResponseDTO create(FilmRequestDTO FilmRequestDTO, String subjectByToken) throws NoSuchFieldException, IllegalAccessException;
    void deleteById(Long id);
    FilmResponseDTO update(FilmRequestDTO FilmRequestDTO) throws NoSuchFieldException, IllegalAccessException;

}
