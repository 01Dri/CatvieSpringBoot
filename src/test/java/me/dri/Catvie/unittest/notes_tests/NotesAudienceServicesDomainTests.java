package me.dri.Catvie.unittest.notes_tests;

import me.dri.Catvie.domain.adapters.services.notes.NotesAudienceServicesImpl;
import me.dri.Catvie.domain.exceptions.notes.InvalidIdException;
import me.dri.Catvie.domain.exceptions.notes.InvalidNoteException;
import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.ports.interfaces.mappers.MapperFilmDomainPort;
import me.dri.Catvie.domain.ports.interfaces.notes.NotesAudienceServicesPort;
import me.dri.Catvie.domain.ports.repositories.FilmRepositoryPort;
import me.dri.Catvie.domain.ports.repositories.NotesAudiencesPort;
import me.dri.Catvie.unittest.mocks.MockFilm;
import me.dri.Catvie.unittest.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
public class NotesAudienceServicesDomainTests {


    @Mock
    MapperFilmDomainPort mapperFilmDomainPort;

    @Mock
    NotesAudiencesPort reposity;

    @Mock
    FilmRepositoryPort filmRepositoryPort;
    NotesAudienceServicesPort service;

    MockFilm mockFilm;

    MockUser mockUser;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockUser = new MockUser();
        this.mockFilm = new MockFilm();
        this.service = new NotesAudienceServicesImpl(this.reposity, this.mapperFilmDomainPort, this.filmRepositoryPort);
    }

    @Test
    void testAddNotesByFilmId() {
        Film filmMock = this.mockFilm.mockFilm();
        FilmResponseDTO filmResponse = this.mockFilm.mockFilmResponseDTO();
        when(this.reposity.addNoteByFilmId(2.0, 1L, 1L)).thenReturn(filmMock);
        when(this.mapperFilmDomainPort.convertFilmToResponseDTO(filmMock)).thenReturn(filmResponse);
        var result = this.service.addNotesByFilmId(2.0, 1L, 1L);
        assertEquals("Evangelion", result.title());
    }

    @Test
    void testAddNotesByFilmIdNull() {
        var result = assertThrows(InvalidIdException.class,
                () -> this.service.addNotesByFilmId(2.0, null, 1L));
        assertEquals("Content 'id' is invalid!!!", result.getMessage());
    }

    @Test
    void testAddNotesByFilmNoteNull() {
        var result = assertThrows(InvalidNoteException.class,
                () -> this.service.addNotesByFilmId(null, 2L, 1L));
        assertEquals("Content 'note' is invalid!!!", result.getMessage());
    }
}
