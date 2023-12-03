package me.dri.Catvie.unittest.filmservicestest;

import me.dri.Catvie.domain.exceptions.NotFoundFilm;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.ports.repositories.FilmRepositoryPort;
import me.dri.Catvie.domain.ports.repositories.NotesAudiencesPort;
import me.dri.Catvie.infra.adapters.FilmAdapter;
import me.dri.Catvie.infra.entities.FilmEntity;
import me.dri.Catvie.infra.jpa.DirectorRepositoryJPA;
import me.dri.Catvie.infra.jpa.FilmRepositoryJPA;
import me.dri.Catvie.infra.jpa.GenreRepositoryJPA;
import me.dri.Catvie.infra.jpa.NotesAudiencesRepositoryJPA;
import me.dri.Catvie.infra.ports.mappers.MapperFilmInfraPort;
import me.dri.Catvie.unittest.mocks.MockFilm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
public class FilmServicesInfraUnitTest {


    @Mock
    FilmRepositoryJPA filmRepositoryJPA;

    @Mock
    GenreRepositoryJPA genreRepositoryJPA;
    @Mock
    DirectorRepositoryJPA directorRepositoryJPA;
    @Mock
    MapperFilmInfraPort mapperEntities;

    @Mock
    NotesAudiencesRepositoryJPA notesAudiencesPort;

    FilmRepositoryPort service;

    MockFilm mockFilm;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockFilm = new MockFilm();
        service = new FilmAdapter(filmRepositoryJPA, mapperEntities, genreRepositoryJPA, directorRepositoryJPA, notesAudiencesPort);
    }
    
    @Test
    void testFindById() {
        FilmEntity mockFilmEntity = this.mockFilm.mockFilmEntity();
        Film mockFilm = this.mockFilm.mockFilm();
        when(this.filmRepositoryJPA.findFilmById(1L)).thenReturn(Optional.of(mockFilmEntity));
        when(this.mapperEntities.convertyFilmEntityToFilm(mockFilmEntity)).thenReturn(mockFilm);
        var result = this.service.findById(1L);
        verify(this.filmRepositoryJPA, times(1)).findFilmById(1L);
        verify(this.mapperEntities, times(1)).convertyFilmEntityToFilm(mockFilmEntity);
        assertEquals(mockFilmEntity.getTitle(), result.getTitle());
    }

    @Test
    void testFindByTitle() {
        FilmEntity mockFilmEntity = this.mockFilm.mockFilmEntity();
        Film mockFilm = this.mockFilm.mockFilm();
        when(this.filmRepositoryJPA.findFilmByTitle("Filme")).thenReturn(Optional.of(mockFilmEntity));
        when(this.mapperEntities.convertyFilmEntityToFilm(mockFilmEntity)).thenReturn(mockFilm);
        var result = this.service.findByTitle("Filme");
        verify(this.filmRepositoryJPA, times(1)).findFilmByTitle("Filme");
        verify(this.mapperEntities, times(1)).convertyFilmEntityToFilm(mockFilmEntity);
        assertEquals(mockFilmEntity.getTitle(), result.getTitle());
    }

    @Test
    void testNotFoundFilmById() {
        when(this.filmRepositoryJPA.findFilmById(1L)).thenReturn(Optional.empty());
        var exception = assertThrows(NotFoundFilm.class, () -> this.service.findById(1L));
        verify(this.filmRepositoryJPA, times(1)).findFilmById(1L);
        verify(this.mapperEntities, times(0)).convertyFilmEntityToFilm(any());
        assertEquals("Film with this id: " + 1L + " does not exist!" , exception.getMessage());
    }

    @Test
    void testNotFoundFilmByTitle() {
        when(this.filmRepositoryJPA.findFilmByTitle("Filme")).thenReturn(Optional.empty());
        var exception = assertThrows(NotFoundFilm.class, () -> this.service.findByTitle("Filme"));
        verify(this.filmRepositoryJPA, times(1)).findFilmByTitle(any());
        verify(this.mapperEntities, times(0)).convertyFilmEntityToFilm(any());
        assertEquals("Film with this title: " + "Filme" + " Does not exist!", exception.getMessage());

    }

    @Test
    void testFindAll() {
        var listFilmsEntityMock = this.mockFilm.mockListFilmsEntity();
        var listFilmsMock = this.mockFilm.mockListFilms();
        when(this.filmRepositoryJPA.findAllFilms()).thenReturn(Optional.of(listFilmsEntityMock));
        when(this.mapperEntities.convertyListFilmsEntityToListFilm(listFilmsEntityMock)).thenReturn(listFilmsMock);
        var result = this.service.findAllFilmEntity();
        verify(this.filmRepositoryJPA, times(1)).findAllFilms();
        verify(this.mapperEntities, times(1)).convertyListFilmsEntityToListFilm(listFilmsEntityMock);
        assertEquals(2, result.size());

    }


    @Test
    void testDeleteFilmById() {
        Film mockFilm = this.mockFilm.mockFilm();
        FilmEntity mockFilmEntity = this.mockFilm.mockFilmEntity();
        when(this.filmRepositoryJPA.findFilmById(mockFilm.getId())).thenReturn(Optional.of(mockFilmEntity));
        var result = this.service.deleteById(mockFilm.getId());
        verify(this.filmRepositoryJPA, times(1)).findFilmById(mockFilm.getId());
        verify(this.notesAudiencesPort, times(1)).deleteByFilmId(any());
        verify(this.filmRepositoryJPA, times(1)).delete(mockFilmEntity);
        assertEquals(mockFilm.getId(), result);
    }

    @Test
    void testNotFoundFilmDeleteById() {
        when(this.filmRepositoryJPA.findFilmById(1L)).thenReturn(Optional.empty());
        var exception = assertThrows(NotFoundFilm.class, () -> this.service.deleteById(1L));
        assertEquals("The film was not found", exception.getMessage());
    }

}
