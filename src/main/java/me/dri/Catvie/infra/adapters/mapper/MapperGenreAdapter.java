package me.dri.Catvie.infra.adapters.mapper;

import me.dri.Catvie.domain.models.entities.Genre;
import me.dri.Catvie.infra.entities.GenreEntity;
import me.dri.Catvie.infra.ports.MapperGenrePort;

public class MapperGenreAdapter implements MapperGenrePort {
    @Override
    public GenreEntity convertGenreToGenreEntity(Genre genre) {
        return new GenreEntity(null, genre.getGenreName());
    }

    @Override
    public Genre convertGenreEntityToGenre(GenreEntity genreEntity) {
        return new Genre(genreEntity.getId(), genreEntity.getGenreName());
    }
}
