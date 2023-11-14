package me.dri.Catvie.infra.ports;

import me.dri.Catvie.domain.models.entities.Genre;
import me.dri.Catvie.infra.entities.GenreEntity;

import java.util.Set;

public interface MapperGenrePort {

    GenreEntity convertGenreToGenreEntity(Genre genre);
    Genre convertGenreEntityToGenre(GenreEntity genreEntity);

    Set<Genre> convertListGenresEntityToGenre(Set<GenreEntity> genreEntities);


}
