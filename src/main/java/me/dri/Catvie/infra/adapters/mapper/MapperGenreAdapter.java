package me.dri.Catvie.infra.adapters.mapper;

import me.dri.Catvie.domain.models.entities.Genre;
import me.dri.Catvie.infra.entities.GenreEntity;
import me.dri.Catvie.infra.ports.MapperGenrePort;

import java.util.Set;
import java.util.stream.Collectors;

public class MapperGenreAdapter implements MapperGenrePort {
    @Override
    public GenreEntity convertGenreToGenreEntity(Genre genre) {
        return new GenreEntity(null, genre.getGenreName());
    }

    @Override
    public Genre convertGenreEntityToGenre(GenreEntity genreEntity) {
        return new Genre(genreEntity.getId(), genreEntity.getGenreName());
    }

    @Override
    public Set<Genre> convertListGenresEntityToGenre(Set<GenreEntity> genreEntities) {
        return genreEntities.stream().map(genreEntity -> new Genre(genreEntity.getId(), genreEntity.getGenreName())).collect(Collectors.toSet());
    }
}
