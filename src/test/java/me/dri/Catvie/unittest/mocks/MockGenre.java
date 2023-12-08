package me.dri.Catvie.unittest.mocks;

import me.dri.Catvie.domain.enums.Genres;
import me.dri.Catvie.domain.models.dto.genre.GenreRequestDTO;
import me.dri.Catvie.domain.models.entities.Genre;
import me.dri.Catvie.infra.entities.GenreEntity;

import java.util.HashSet;
import java.util.Set;

public class MockGenre {


    public GenreRequestDTO mockGenreDTO() {
        return new GenreRequestDTO(Genres.ACTION);
    }

    public Set<GenreRequestDTO> mockGenres() {
        HashSet<GenreRequestDTO> genreDTOS = new HashSet<>();
        genreDTOS.add(mockGenreDTO());
        genreDTOS.add(mockGenreDTO());
        return genreDTOS;
    }

    public Set<Genre> mockSetGenres() {
        HashSet<Genre> genreDTOS = new HashSet<>();
        genreDTOS.add(mockGenre());
        genreDTOS.add(mockGenre());
        return genreDTOS;
    }

    public Genre mockGenre() {
        return new Genre(1L, Genres.ACTION);
    }

    public GenreEntity mockGenreEntity() {
        return new GenreEntity(1L, Genres.ACTION);
    }


}
