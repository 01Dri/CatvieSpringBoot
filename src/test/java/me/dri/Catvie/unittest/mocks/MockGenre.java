package me.dri.Catvie.unittest.mocks;

import me.dri.Catvie.domain.enums.Genres;
import me.dri.Catvie.domain.models.dto.genre.GenreDTO;
import me.dri.Catvie.domain.models.entities.Genre;

import java.util.HashSet;
import java.util.Set;

public class MockGenre {


    public GenreDTO mockGenreDTO() {
        return new GenreDTO(Genres.ACTION);
    }

    public Set<GenreDTO> mockGenres() {
        HashSet<GenreDTO> genreDTOS = new HashSet<>();
        genreDTOS.add(mockGenreDTO());
        genreDTOS.add(mockGenreDTO());
        return genreDTOS;
    }

    public Genre mockGenre() {
        return new Genre(1L, Genres.ACTION);
    }


}
