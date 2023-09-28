package me.dri.Catvie.unittest.filmservicestest;

import me.dri.Catvie.entity.interfaces.FilmCrudInterface;
import me.dri.Catvie.entity.models.Film;
import me.dri.Catvie.entity.models.dto.FilmDto;
import me.dri.Catvie.infra.interfaces.IDozerMapper;
import me.dri.Catvie.infra.repositories.FilmRepositoryJPA;
import me.dri.Catvie.usecases.FilmCrudServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

public class FilmServicesTest {


    @Mock
    private FilmRepositoryJPA filmRepositoryJPA;

    @Mock
    private IDozerMapper mapper;

    private FilmCrudInterface serviceCrud;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        serviceCrud = new FilmCrudServiceImpl(filmRepositoryJPA, mapper);
    }

    @Test
    void testCreateFilm() {
        FilmDto filmCreate = mock(FilmDto.class);
        Film filmSaved = mock(Film.class);
        when(this.filmRepositoryJPA.save(any())).thenReturn(filmSaved);
        this.serviceCrud.create(filmCreate);
        verify(this.mapper, times(1)).map(any(), any());
        verify(this.filmRepositoryJPA, times(1)).save(any());
    }

}
