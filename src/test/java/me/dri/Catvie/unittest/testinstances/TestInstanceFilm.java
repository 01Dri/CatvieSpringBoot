package me.dri.Catvie.unittest.testinstances;

import me.dri.Catvie.domain.factory.impl.FilmFactory;
import me.dri.Catvie.domain.factory.interfaces.IFactoryFilm;
import me.dri.Catvie.infra.entities.FilmEntity;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestInstanceFilm {

    @Test
    void testCreateInstanceFilm() {
        // Verificando se a instanciação está correta
        FilmEntity film = new FilmEntity();
        film.setTitle("Guardians");
        assertEquals("Guardians", film.getTitle());

    }
    @Test
    void testCreateInstanceFilmByFactory() {
        // Fazendo a instanciação utilizando Factory
        IFactoryFilm factoryFilm = new FilmFactory();
        FilmEntity film = factoryFilm.getInstance("BOLT", null, "English",
                null, "Dan Folgelman",
                new Date(), 96,
                null, "Walt Disney Pictures, Walt Disney Animation",
                90.0, 74.0, "teste");
        assertEquals("BOLT", film.getTitle());
        assertEquals(90.0, film.getAverage_rating_critic());
    }
}
