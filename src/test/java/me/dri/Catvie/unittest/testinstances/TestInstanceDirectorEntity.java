package me.dri.Catvie.unittest.testinstances;

import me.dri.Catvie.infra.adapters.entities.DirectorEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestInstanceDirectorEntity {

    @Test
    void testCreateInstanceFilm() {
        // Verificando se a instanciação está correta
        DirectorEntity directorEntity = new DirectorEntity();
        directorEntity.setName("Diego");
        assertEquals("Diego", directorEntity.getName());

    }

}
