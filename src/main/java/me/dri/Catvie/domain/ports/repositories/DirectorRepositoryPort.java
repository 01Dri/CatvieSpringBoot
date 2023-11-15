package me.dri.Catvie.domain.ports.repositories;

import me.dri.Catvie.domain.models.entities.Director;

import java.util.List;

public interface DirectorRepositoryPort {

    Director findById(Long id);

    List<Director> findAll();

    Director findByName(String name);

    void create(Director filmDto);

    void save(Director film);

    void delete(Director film);

    Director update(Director film);


}
