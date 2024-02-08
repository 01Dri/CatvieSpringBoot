package me.dri.Catvie.domain.adapters.services.genre;

import me.dri.Catvie.domain.models.core.Genre;
import me.dri.Catvie.domain.models.dto.genre.GenreRequestDTO;
import me.dri.Catvie.domain.models.dto.genre.GenreResponseDTO;
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
    public Set<GenreResponseDTO> findAll() {
        var genres = this.repositoryPort.findAll();
        return genres.stream().map(genre -> new GenreResponseDTO(genre.getId(), genre.getGenreName())).collect(Collectors.toSet());
    }

    @Override
    public Set<Genre> getAllGenresByGenreDTO(Set<GenreRequestDTO> genreDTOS) {
        Set<Genre>  genresHash = new HashSet<>();
        for (GenreRequestDTO genreDTO : genreDTOS) {
            var genreData = this.repositoryPort.findByName(genreDTO.getGenreName().name());
            genresHash.add(new Genre(genreData.getId(), genreData.getGenreName()));
        }
        return genresHash;
    }
}
