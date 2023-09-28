package me.dri.Catvie.unittest;


import me.dri.Catvie.entity.models.Film;
import me.dri.Catvie.entity.models.dto.FilmDto;
import me.dri.Catvie.infra.interfaces.IDozerMapper;
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
    private IDozerMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMapperDozer() {
        FilmDto filmDto = mock(FilmDto.class);
        Film film = mock(Film.class);
        when(film.getTitle()).thenReturn("Diego");
        when(this.mapper.map(any(), any())).thenReturn(film);
        Film result = this.mapper.map(filmDto, Film.class);
        when(film.getTitle()).thenReturn("Diego");
        assertEquals("Diego", result.getTitle());

    }

}
