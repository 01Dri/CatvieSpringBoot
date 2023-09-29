package me.dri.Catvie.entity.interfaces;

import me.dri.Catvie.entity.models.Film;
import me.dri.Catvie.entity.models.dto.FilmDto;

import java.util.Date;
import java.util.List;

public interface FilmCrudInterface {

    Film findById(Long id);
    List<FilmDto> findAll();
    FilmDto findByTitle(String title);
    void create(FilmDto filmDto);
    void save(FilmDto film);
    void delete(FilmDto film);

}
