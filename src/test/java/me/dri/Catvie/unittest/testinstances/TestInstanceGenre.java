package me.dri.Catvie.unittest.testinstances;

import me.dri.Catvie.domain.enums.Genres;
import me.dri.Catvie.infra.entities.GenreEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestInstanceGenre {


    @Test
    void testeInstanceGenre() {
        GenreEntity genre = new GenreEntity(1L, Genres.ACTION);
        assertEquals(Genres.ACTION, genre.getGenreName());
    }
}
