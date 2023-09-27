package me.dri.Catvie.unittest.testinstances;

import me.dri.Catvie.entity.enums.Genres;
import me.dri.Catvie.entity.models.Genre;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class TestInstanceGenre {


    @Test
    void testeInstanceGenre() {
        Genre genre = new Genre(1L, Genres.ACTION);
        assertEquals(Genres.ACTION, genre.getGenreName());
    }
}
