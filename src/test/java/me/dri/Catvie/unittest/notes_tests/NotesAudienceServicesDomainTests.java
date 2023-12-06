package me.dri.Catvie.unittest.notes_tests;

import me.dri.Catvie.domain.adapters.services.notes.NotesAudienceServicesImpl;
import me.dri.Catvie.domain.exceptions.film.InvalidTitleFilmException;
import me.dri.Catvie.domain.exceptions.notes.InvalidIdException;
import me.dri.Catvie.domain.exceptions.notes.InvalidNoteException;
import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;
import me.dri.Catvie.domain.models.dto.notes.NotesResponseDTO;
import me.dri.Catvie.domain.models.dto.user.UserDTO;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.models.entities.NotesAudience;
import me.dri.Catvie.domain.models.entities.User;
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


    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        this.service = new NotesAudienceServicesImpl(this.reposity, this.mapperFilmDomainPort, this.filmRepositoryPort);
    }

//    @Test
//    void testAddNotesByFilmId() {
//        Film filmMock = this.mockFilm.mockFilm();
//        FilmResponseDTO filmResponse = this.mockFilm.mockFilmResponseDTO();
//        UserDTO userDTO = this.mockUser.mockUserDTO();
//        when(this.reposity.addNoteByFilmId(2.0, 1L, userDTO.email())).thenReturn(filmMock);
//        when(this.mapperFilmDomainPort.convertFilmToResponseDTO(filmMock)).thenReturn(filmResponse);
//        var result = this.service.addNotesByFilmId(2.0, 1L, userDTO.email());
//        assertEquals("Evangelion", result.title());
//    }
//
//    @Test
//    void testAddNotesByFilmTitle() {
//        Film filmMock = this.mockFilm.mockFilm();
//        FilmResponseDTO filmResponse = this.mockFilm.mockFilmResponseDTO();
//        UserDTO userDTO = this.mockUser.mockUserDTO();
//        when(this.reposity.addNoteByFilmTitle(2.0, filmMock.getTitle(), userDTO.email())).thenReturn(filmMock);
//        when(this.mapperFilmDomainPort.convertFilmToResponseDTO(filmMock)).thenReturn(filmResponse);
//        var result = this.service.addNotesByFilmTitle(2.0, filmMock.getTitle(), userDTO.email());
//        assertEquals("Evangelion", result.title());
//    }

    @Test
    void testChangeNotesByFilmId() {
        var mockUserTest = mock(User.class);
        var mockFilmTest = mock(Film.class);
        var mockUserDTOTest = mock(UserDTO.class);

        NotesAudience notesAudienceMock = new NotesAudience(1L, mockFilmTest, mockUserTest, 7.5, 5.4);
        when(this.reposity.changeNoteByFilmId(2.0, mockFilmTest.getId(), mockUserDTOTest.email() ,1L)).thenReturn(notesAudienceMock);
        var result = this.service.changeNoteByFilmId(2.0, mockFilmTest.getId(), mockUserDTOTest.email(), 1L);
        assertEquals(7.5, result.noteAdded());
    }


    @Test
    void testAddNotesByFilmIdNull() {
        var result = assertThrows(InvalidIdException.class,
                () -> this.service.addNotesByFilmId(2.0, null, null));
        assertEquals("Content 'id' is invalid!!!", result.getMessage());
    }

    @Test
    void testAddNotesByFilmNoteNull() {
        var result = assertThrows(InvalidNoteException.class,
                () -> this.service.addNotesByFilmId(null, 2L, null));
        assertEquals("Content 'note' is invalid!!!", result.getMessage());
    }
    @Test
    void testAddNotesByFilmTitleNull() {
        var result = assertThrows(InvalidTitleFilmException.class,
                () -> this.service.addNotesByFilmTitle(2.5, null, "teste@gmail.com"));
        assertEquals("Content 'title' is null", result.getMessage());
    }
}
