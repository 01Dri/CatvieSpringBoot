package me.dri.Catvie.domain.ports.repositories;

import me.dri.Catvie.domain.models.core.Genre;

import java.util.Set;

public interface GenreRepositoryPort {

    Genre findById(Long id);

    Set<Genre> findAll();

    Genre findByName(String title);

}
