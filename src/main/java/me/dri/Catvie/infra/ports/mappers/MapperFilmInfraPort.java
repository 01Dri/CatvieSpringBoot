package me.dri.Catvie.infra.ports.mappers;

import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.infra.entities.FilmEntity;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface MapperFilmInfraPort {

    Film convertyFilmEntityToFilm(FilmEntity filmEntity);

    List<Film> convertyListFilmsEntityToListFilm(List<FilmEntity> filmEntities);

    FilmEntity convertyFilmToFilmEntity(Film film);

}
