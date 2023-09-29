package me.dri.Catvie.unittest.dozertest;


import me.dri.Catvie.infra.adapters.entities.FilmEntity;
import me.dri.Catvie.domain.models.dto.FilmDTO;
import me.dri.Catvie.domain.ports.interfaces.DozerMapperPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DozerMapperTeste {

    @Mock
    private DozerMapperPort mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMapperDozer() {
        FilmDTO filmDto = mock(FilmDTO.class);
        FilmEntity film = mock(FilmEntity.class);
        when(film.getTitle()).thenReturn("Diego");
        when(this.mapper.map(any(), any())).thenReturn(film);
        FilmEntity result = this.mapper.map(filmDto, FilmEntity.class);
        when(film.getTitle()).thenReturn("Diego");
        assertEquals("Diego", result.getTitle());

    }

}
