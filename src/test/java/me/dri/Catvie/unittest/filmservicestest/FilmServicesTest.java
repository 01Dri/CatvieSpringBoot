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

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    void testSaveFilm() {
        FilmDto filmDto = mock(FilmDto.class);
        Film filmToSave = mock(Film.class);
        when(this.mapper.map(filmDto, Film.class)).thenReturn(filmToSave);
        this.serviceCrud.save(filmDto);
        verify(this.filmRepositoryJPA, times(1)).save(filmToSave);
    }

    @Test
    void testFindFilmByTitle() {
        Film film = mock(Film.class);
        FilmDto filmDto = mock(FilmDto.class);
        when(this.filmRepositoryJPA.findFilmByTitle("film test 1")).thenReturn(film);
        when(this.mapper.map(any(), any())).thenReturn(filmDto);
        var result = this.serviceCrud.findByTitle("film test 1");
        System.out.println(result);
        assertTrue(result instanceof FilmDto);
        verify(this.mapper, times(1)).map(any(), any());
    }

    @Test
    void testFindFilmById() {
        Film filmReturn = mock(Film.class);
        when(this.filmRepositoryJPA.findFilmById(1L)).thenReturn(filmReturn);
        var result = this.serviceCrud.findById(1L);
        assertEquals(result, filmReturn);
        verify(this.filmRepositoryJPA, times(1)).findFilmById(any());
    }

    @Test
    void testFindAll() {
        List<Film> films = Collections.singletonList(mock(Film.class));
        List<FilmDto> filmDtos = Collections.singletonList(mock(FilmDto.class));
        when(this.filmRepositoryJPA.findAllFilms()).thenReturn(films);
        when(this.mapper.mapCollections(any(), any())).thenReturn(Collections.singletonList(filmDtos));
        var result = this.serviceCrud.findAll();

        // Checking if result this a ListDto
        assertTrue(result instanceof List<FilmDto>);
        verify(this.mapper, times(1)).mapCollections(any(), any());
    }

    @Test
    void testDeleteFilm() {
        FilmDto filmDto = mock(FilmDto.class);
        Film filmToDelete = mock(Film.class);
        when(mapper.map(filmDto, Film.class)).thenReturn(filmToDelete);
        this.serviceCrud.delete(filmDto);
        verify(this.mapper, times(1)).map(filmDto, Film.class);
        verify(this.filmRepositoryJPA, times(1)).delete(filmToDelete);
    }

}
