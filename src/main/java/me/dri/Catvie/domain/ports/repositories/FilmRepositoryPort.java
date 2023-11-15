package me.dri.Catvie.domain.ports.repositories;

import me.dri.Catvie.domain.models.entities.Film;

import java.util.List;

public interface FilmRepositoryPort {

    Film findById(Long id);

    List<Film> findAll();

    Film findByTitle(String title);

    void create(Film filmDto);

    void save(Film film);

    void delete(Film film);

    Film update(Film film);
}
