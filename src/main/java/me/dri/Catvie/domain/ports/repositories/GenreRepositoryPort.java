package me.dri.Catvie.domain.ports.repositories;

import me.dri.Catvie.domain.models.dto.genre.GenreDTO;
import me.dri.Catvie.domain.models.entities.Genre;

import java.util.Set;

public interface GenreRepositoryPort {

    Genre findById(Long id);

    Set<Genre> findAll();

    Genre findByName(String title);

    void create(Genre filmDto);

    void save(Genre film);

    void delete(Genre film);

    Genre update(Genre film);

}
