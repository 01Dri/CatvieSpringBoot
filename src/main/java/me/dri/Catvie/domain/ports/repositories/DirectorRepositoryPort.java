package me.dri.Catvie.domain.ports.repositories;

import me.dri.Catvie.domain.models.core.Director;

import java.util.List;

public interface DirectorRepositoryPort {

    Director findById(Long id);

    Director findByName(String name);
}