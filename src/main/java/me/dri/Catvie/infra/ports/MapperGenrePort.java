package me.dri.Catvie.infra.ports;

import me.dri.Catvie.domain.models.entities.Genre;
import me.dri.Catvie.infra.entities.GenreEntity;

public interface MapperGenrePort {

    GenreEntity convertGenreToGenreEntity(Genre genre);
    Genre convertGenreEntityToGenre(GenreEntity genreEntity);

}
