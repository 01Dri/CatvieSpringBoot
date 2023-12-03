package me.dri.Catvie.unittest.notes_tests;

import me.dri.Catvie.domain.exceptions.NotFoundFilm;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.ports.repositories.NotesAudiencesPort;
import me.dri.Catvie.infra.adapters.NotesAudienceAdapter;
import me.dri.Catvie.infra.entities.FilmEntity;
import me.dri.Catvie.infra.entities.UserEntity;
import me.dri.Catvie.infra.jpa.FilmRepositoryJPA;
import me.dri.Catvie.infra.jpa.NotesAudiencesRepositoryJPA;
import me.dri.Catvie.infra.jpa.UserRepositoryJPA;
import me.dri.Catvie.infra.ports.mappers.MapperFilmInfraPort;
import me.dri.Catvie.unittest.mocks.MockFilm;
import me.dri.Catvie.unittest.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
public class NotesAudienceServicesInfraTests {


    @Mock
    FilmRepositoryJPA filmRepositoryJPA;

    @Mock
    NotesAudiencesRepositoryJPA notesAudiencesRepositoryJPA;

    @Mock
    UserRepositoryJPA userRepositoryJPA;

    @Mock
    MapperFilmInfraPort mapperFilmInfraPort;


    NotesAudiencesPort service;


    MockFilm mockFilm;

    MockUser mockUser;


    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockFilm = new MockFilm();
        this.mockUser = new MockUser();
        this.service = new NotesAudienceAdapter(this.filmRepositoryJPA, this.notesAudiencesRepositoryJPA, this.userRepositoryJPA, this.mapperFilmInfraPort);
    }

    @Test
    void testAddNotesByFilmId() {
        FilmEntity mockFilmEntity = this.mockFilm.mockFilmEntity();
        UserEntity mockUserEntity = this.mockUser.mockUserEntity();
        when(this.filmRepositoryJPA.findFilmById(1L)).thenReturn(Optional.of(mockFilmEntity));
        Film mockFilm = this.mockFilm.mockFilm();
        when(this.mapperFilmInfraPort.convertyFilmEntityToFilm(mockFilmEntity)).thenReturn(mockFilm);
        when(this.userRepositoryJPA.findById(1L)).thenReturn(Optional.of(mockUserEntity));
        var result = this.service.addNoteByFilmId(2.0, 1L, 1L);
        assertEquals("Evangelion", result.getTitle());
    }
    @Test
    void testAddNotesByFilmIdNotFound() {
        when(this.filmRepositoryJPA.findFilmById(1L)).thenReturn(Optional.empty());
        var exception = assertThrows(NotFoundFilm.class, () -> this.service.addNoteByFilmId(2.0, 1L, 1L));
        verify(this.mapperFilmInfraPort, times(0)).convertyFilmEntityToFilm(any());
        assertEquals("Film by id not found", exception.getMessage());

    }

    @Test
    void testGetAverageNotesByFilmId() {
        List<Double> notesMock = List.of(7.2, 8.9, 3.2, 4.5);
        double sumMock = 0;
        for (Double n:
             notesMock) {
            sumMock += n;
        }
        Double averageMock = sumMock / notesMock.size();
        Double averageMockRounded = Math.round(averageMock * 10.0) / 10.0;
        when(this.notesAudiencesRepositoryJPA.findAllNotesByFilmId(1L)).thenReturn(notesMock);
        var result = this.service.getAverageNotesByFilmId(1L);
        assertEquals(averageMockRounded, result);
    }
}
