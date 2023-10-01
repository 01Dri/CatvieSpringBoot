package me.dri.Catvie.unittest.filmservicestest;

import me.dri.Catvie.domain.adapters.services.FilmServiceImpl;
import me.dri.Catvie.domain.exceptions.ContentIsMissing;
import me.dri.Catvie.domain.models.dto.FilmDTO;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.ports.interfaces.FilmServicePort;
import me.dri.Catvie.domain.ports.interfaces.MapperEntitiesPort;
import me.dri.Catvie.domain.ports.repositories.FilmRepositoryPort;
import me.dri.Catvie.unittest.utils.MockEntities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class FilmServicesTest  {

    FilmServicePort service;
    @Mock
    FilmRepositoryPort repository;
    @Mock
    MapperEntitiesPort mapperPort;
    MockEntities mockEntitys;




    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new FilmServiceImpl(repository, mapperPort);
        mockEntitys = new MockEntities();

    }

//    @Test
//    void testUpdateFilm() {
//        FilmDTO filmDto = mock(FilmDTO.class);
//        FilmEntity film = mock(FilmEntity.class);
//        when(this.repositoryJPA.findFilmById(any())).thenReturn(film);
//        this.service.update();
//
//    }

    @Test
    void testCreateFilm() {
        FilmDTO filmCreate = this.mockEntitys.mockFilmDto();
        Film filmSaved = this.mockEntitys.mockFilm();
        this.service.create(filmCreate);
        verify(this.mapperPort, times(1)).convertFilmDtoToFilm(any());
        verify(this.repository, times(1)).save(any());
    }

    @Test
    void testExceptionMissingContentInCreateFilm() {
        FilmDTO filmDtoInvalid = this.mockEntitys.mockFilmDtoWithNullValue();
        assertThrows(ContentIsMissing.class, () -> this.service.create(filmDtoInvalid));
        verify(this.mapperPort, never()).convertFilmToDto(any());
        verify(this.repository, never()).save(any());

    }

    @Test
    void testSaveFilm() {
        FilmDTO filmDto = mock(FilmDTO.class);
        Film filmToSave = mock(Film.class);
        when(this.mapperPort.convertFilmDtoToFilm(filmDto)).thenReturn(filmToSave);
        this.service.save(filmDto);
        verify(this.repository, times(1)).save(filmToSave);
    }

    @Test
    void testFindFilmByTitle() {
        Film film = mock(Film.class);
        FilmDTO filmDto = mock(FilmDTO.class);
        when(this.repository.findByTitle("film test 1")).thenReturn(film);
        when(this.mapperPort.convertFilmToDto(any())).thenReturn(filmDto);
        var result = this.service.findByTitle("film test 1");
        System.out.println(result);
        assertTrue(result instanceof FilmDTO);
        verify(this.mapperPort, times(1)).convertFilmToDto(any());
    }

    @Test
    void testFindFilmById() {
        Film film = mock(Film.class);
        FilmDTO filmDTO = mock(FilmDTO.class);
        when(this.repository.findById(1L)).thenReturn(film);
        when(this.mapperPort.convertFilmToDto(any())).thenReturn(filmDTO);
        var result = this.service.findById(1L);
        System.out.println(result);
        assertEquals(result, filmDTO);
        verify(this.repository, times(1)).findById(any());
    }

    @Test
    void testFindAll() {
        List<Film> films = Collections.singletonList(mock(Film.class));
        List<FilmDTO> filmDtos = Collections.singletonList(mock(FilmDTO.class));
        when(this.repository.findAll()).thenReturn(films);
        when(this.mapperPort.convertListFilmToListDto(any())).thenReturn(filmDtos);
        var result = this.service.findAll();

        // Checking if result this a ListDto
        assertTrue(result instanceof List<FilmDTO>);
        verify(this.mapperPort, times(1)).convertListFilmToListDto(any());
    }

    @Test
    void testDeleteFilm() {
        Film filmToDelete = this.mockEntitys.mockFilm();
        when(this.repository.findById(1L)).thenReturn(filmToDelete);
        this.service.deleteById(1L);
        verify(this.repository, times(1)).delete(filmToDelete);
    }

}
