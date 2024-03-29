package me.dri.Catvie.domain.ports.repositories;

import me.dri.Catvie.domain.models.core.Film;

import java.util.List;

public interface FilmRepositoryPort {

    Film findById(Long id);

    List<Film> findAllFilmEntity();

    Film findByTitle(String title);

    Film create(Film FilmRequestDTO, String subjectEmail);

    void delete(Film film);

    Film update(Film film);

    Long deleteById(Long id);

}
