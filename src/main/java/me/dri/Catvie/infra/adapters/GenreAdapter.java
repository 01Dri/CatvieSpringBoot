package me.dri.Catvie.infra.adapters;

import me.dri.Catvie.domain.enums.Genres;
import me.dri.Catvie.domain.exceptions.InvalidGenre;
import me.dri.Catvie.domain.models.core.Genre;
import me.dri.Catvie.domain.ports.repositories.GenreRepositoryPort;
import me.dri.Catvie.infra.entities.GenreEntity;
import me.dri.Catvie.infra.repositoriesjpa.GenreRepositoryJPA;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class GenreAdapter  implements GenreRepositoryPort {

    private final GenreRepositoryJPA repositoryJPA;

    private final ModelMapper modelMapper;


    @Autowired
    public GenreAdapter(GenreRepositoryJPA repositoryJPA, ModelMapper modelMapper) {
        this.repositoryJPA = repositoryJPA;
        this.modelMapper = modelMapper;
    }

    @Override
    public Genre findById(Long id) {
        return null;
    }

    @Override
    public Set<Genre> findAll() {
        Set<GenreEntity> allGenres = this.repositoryJPA.findAllSet().orElseThrow(() -> new InvalidGenre("Invalid genre"));
        return allGenres.stream().map(genre -> this.modelMapper.map(genre, Genre.class)).collect(Collectors.toSet());
    }

    @Override
    public Genre findByName(String title) {
        try {
            Genres genres = Genres.valueOf(title);
            GenreEntity genreByName = this.repositoryJPA.findBygenreName(genres).orElseThrow(() -> new InvalidGenre("Invalid genre"));
            return this.modelMapper.map(genreByName, Genre.class);
        } catch (HttpMessageNotReadableException e) {
            throw new InvalidGenre("Genre " + title + " Not exist"); // Failed to find genre by name
        }
    }
}