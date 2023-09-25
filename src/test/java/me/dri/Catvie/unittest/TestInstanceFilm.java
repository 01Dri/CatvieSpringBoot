package me.dri.Catvie.unittest;
import me.dri.Catvie.entity.enums.Genres;
import me.dri.Catvie.entity.factory.impl.FilmFactory;
import me.dri.Catvie.entity.factory.interfaces.IFactoryFilm;
import me.dri.Catvie.entity.models.Film;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class TestInstanceFilm {

    @Test
    void testCreateInstanceFilm() {
        // Verificando se a instanciação da entity funciona
        Film film = new Film();
        film.setTitle("Guardians");
        assertEquals("Guardians", film.getTitle());

    }

    @Test
    void testCreateInstanceFilmByFactory() {
        IFactoryFilm factoryFilm = new FilmFactory();
        List<Genres> genres = new ArrayList<>();
        genres.add(Genres.ADVENTURE);
        genres.add(Genres.ANIMATION);
        Film film = factoryFilm.getInstance("BOLT", genres, "English", "Chris Willians", "Dan Folgelman", new Date(), 96, "Walt Disney", "Walt Disney Pictures, Walt Disney Animation", 90.0, 74.0);
        assertEquals("BOLT", film.getTitle());
        assertEquals(90.0, film.getAverage_rating_critic());
    }
}
