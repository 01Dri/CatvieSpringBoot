package me.dri.Catvie.infra.ports.mappers;

import me.dri.Catvie.domain.models.entities.Genre;
import me.dri.Catvie.infra.entities.GenreEntity;
import org.springframework.stereotype.Component;

import java.util.Set;
@Component
public interface MapperGenreInfraPort {

    GenreEntity convertGenreToGenreEntity(Genre genre);
    Genre convertGenreEntityToGenre(GenreEntity genreEntity);

    Set<Genre> convertListGenresEntityToGenre(Set<GenreEntity> genreEntities);


}
