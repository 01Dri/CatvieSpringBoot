package me.dri.Catvie.entity.interfaces;

import me.dri.Catvie.entity.models.Director;
import me.dri.Catvie.entity.models.Distributor;
import me.dri.Catvie.entity.models.Film;
import me.dri.Catvie.entity.models.Genre;
import me.dri.Catvie.entity.models.dto.FilmDto;

import java.util.Date;
import java.util.List;

public interface FilmCrudInterface {

    Film findById(Long id);
    List<Film> findAll();
    Film findByTitle(String title);
    void create(FilmDto filmDto);
    Long save(Film film);
    Long delete(Film film);

}
