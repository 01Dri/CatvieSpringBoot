package me.dri.Catvie.unittest.testinstances;

import me.dri.Catvie.entity.models.Director;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestInstanceDirector {

    @Test
    void testCreateInstanceFilm() {
        // Verificando se a instanciação está correta
        Director director = new Director();
        director.setName("Diego");
        assertEquals("Diego", director.getName());

    }

}
