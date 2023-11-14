package me.dri.Catvie.domain.adapters.services;

import me.dri.Catvie.domain.exceptions.InvalidGenre;
import me.dri.Catvie.domain.models.dto.genre.GenreDTO;
import me.dri.Catvie.domain.models.entities.Genre;
import me.dri.Catvie.domain.ports.interfaces.genre.GenreServicesPort;
import me.dri.Catvie.domain.ports.repositories.GenreRepositoryPort;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class GenreServiceImpl implements GenreServicesPort {

    private final GenreRepositoryPort repositoryPort;

    public GenreServiceImpl(GenreRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public GenreDTO findById(Long id) {
        return null;
    }

    @Override
    public Set<GenreDTO> findAll() {
        var genres = this.repositoryPort.findAll();
        return genres.stream().map(genre -> new GenreDTO(genre.getGenreName())).collect(Collectors.toSet());
    }

    @Override
    public GenreDTO findByName(String title) {
        Genre genre = this.repositoryPort.findByName(title);
        return new GenreDTO(genre.getGenreName());
    }

    @Override
    public void create(GenreDTO genre) {

    }

    @Override
    public void save(GenreDTO genre) {

    }

    @Override
    public void delete(GenreDTO genre) {

    }

    @Override
    public Set<GenreDTO> verifyExistingGenres(Set<GenreDTO> genreDTOS) {
        Set<GenreDTO>  response = new HashSet<>();
        for (GenreDTO genreDTO : genreDTOS) {
            var genreData = this.repositoryPort.findByName(genreDTO.genreName().name());
            if (genreData == null) {
                throw  new InvalidGenre("Genre name "  + genreDTO.genreName() + " Not exists");
            }
            response.add(new GenreDTO(genreData.getGenreName()));
        }
        return response;
    }
}
