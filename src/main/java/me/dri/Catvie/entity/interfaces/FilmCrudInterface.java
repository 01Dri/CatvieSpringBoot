package me.dri.Catvie.entity.interfaces;

import me.dri.Catvie.entity.models.Film;

import java.util.List;

public interface FilmCrudInterface {

    Film findById(Long id);
    List<Film> findAll();
    Film findByTitle(String title);
    Long save(Film film);
    Long delete(Film film);

}
