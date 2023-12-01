package me.dri.Catvie.infra.adapters;

import me.dri.Catvie.domain.enums.Genres;
import me.dri.Catvie.domain.exceptions.InvalidGenre;
import me.dri.Catvie.domain.models.entities.Genre;
import me.dri.Catvie.domain.ports.repositories.GenreRepositoryPort;
import me.dri.Catvie.infra.entities.GenreEntity;
import me.dri.Catvie.infra.jpa.GenreRepositoryJPA;
import me.dri.Catvie.infra.ports.mappers.MapperGenreInfraPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class GenreAdapter  implements GenreRepositoryPort {

    private final GenreRepositoryJPA repositoryJPA;

    private final MapperGenreInfraPort mapperGenrePort;

    @Autowired
    public GenreAdapter(GenreRepositoryJPA repositoryJPA, MapperGenreInfraPort mapperGenrePort) {
        this.repositoryJPA = repositoryJPA;
        this.mapperGenrePort = mapperGenrePort;
    }

    @Override
    public Genre findById(Long id) {
        return null;
    }

    @Override
    public Set<Genre> findAll() {
        var genres = this.repositoryJPA.findAllSet().orElseThrow(() -> new InvalidGenre("Invalid genre"));
        return this.mapperGenrePort.convertListGenresEntityToGenre(genres);
    }

    @Override
    public Genre findByName(String title) {
        try {
            Genres genres = Genres.valueOf(title);
            GenreEntity genreEntity= this.repositoryJPA.findBygenreName(genres).orElseThrow(() -> new InvalidGenre("Invalid genre"));
            return this.mapperGenrePort.convertGenreEntityToGenre(genreEntity);
        } catch (HttpMessageNotReadableException e) {
            throw  new InvalidGenre("Genre " + title + " Not exist");
        }
    }

    @Override
    public void create(Genre genre) {

    }

    @Override
    public void save(Genre genre) {

    }

    @Override
    public void delete(Genre genre) {

    }

    @Override
    public Genre update(Genre film) {
        return null;
    }

}
