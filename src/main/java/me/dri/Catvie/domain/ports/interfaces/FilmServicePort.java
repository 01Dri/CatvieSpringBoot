package me.dri.Catvie.domain.ports.interfaces;

import me.dri.Catvie.domain.models.dto.FilmDTO;

import java.util.List;

public interface FilmServicePort {

    FilmDTO findById(Long id);
    List<FilmDTO> findAll();
    FilmDTO findByTitle(String title);
    void create(FilmDTO filmDto);
    void save(FilmDTO film);
    void deleteById(Long id);

}
