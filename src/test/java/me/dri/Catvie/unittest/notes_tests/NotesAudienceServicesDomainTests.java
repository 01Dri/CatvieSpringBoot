package me.dri.Catvie.unittest.notes_tests;

import me.dri.Catvie.domain.adapters.services.notes.NotesAudienceServicesImpl;
import me.dri.Catvie.domain.exceptions.film.InvalidTitleFilmException;
import me.dri.Catvie.domain.exceptions.notes.InvalidIdException;
import me.dri.Catvie.domain.exceptions.notes.InvalidNoteException;
import me.dri.Catvie.domain.models.dto.notes.NotesResponseDTO;
import me.dri.Catvie.domain.models.dto.user.UserDTO;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.models.entities.NotesAudience;
import me.dri.Catvie.domain.models.entities.User;
import me.dri.Catvie.domain.ports.repositories.NotesAudiencesPort;
import me.dri.Catvie.unittest.mocks.MockFilm;
import me.dri.Catvie.unittest.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NotesAudienceServicesDomainTests {



    @Mock
    NotesAudiencesPort repository;

    @InjectMocks
    NotesAudienceServicesImpl service;

    MockFilm mockFilm;
    MockUser mockUser;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockFilm = new MockFilm();
        mockUser = new MockUser();

    }

    @Test
    void testAddNotesByFilmId() {

        // Constants
        final Long ID_DEFAULT_FOR_TESTS = 1L;
        final String EMAIL_DEFAULT_FOR_TESTS  = "diego@gmail.com";
        final Double NOTE_DEFAULT_FOR_TESTS = 3.5;

        //Mocks
        Film mockFilmTest = this.mockFilm.mockFilm();
        User mockUserTest = this.mockUser.mockUser();
        NotesAudience notesAudienceMock = new NotesAudience(ID_DEFAULT_FOR_TESTS, mockFilmTest, mockUserTest, NOTE_DEFAULT_FOR_TESTS, 5.4);

        // Stubs
        when(this.repository.addNoteByFilmId(NOTE_DEFAULT_FOR_TESTS, ID_DEFAULT_FOR_TESTS, EMAIL_DEFAULT_FOR_TESTS)).thenReturn(notesAudienceMock);

        var result = this.service.addNotesByFilmId(NOTE_DEFAULT_FOR_TESTS, ID_DEFAULT_FOR_TESTS, EMAIL_DEFAULT_FOR_TESTS);
        verify(this.repository, times(1)).addNoteByFilmId(NOTE_DEFAULT_FOR_TESTS, ID_DEFAULT_FOR_TESTS, EMAIL_DEFAULT_FOR_TESTS);
        assertInstanceOf(NotesResponseDTO.class, result);

    }

    @Test
    void testAddNotesByFilmTitle() {
        // Constants
        final Long ID_DEFAULT_FOR_TESTS = 1L;
        final String EMAIL_DEFAULT_FOR_TESTS  = "diego@gmail.com";
        final Double NOTE_DEFAULT_FOR_TESTS = 3.5;
        final String TITLE_FILM_DEFAULT_FOR_TESTS = "O Convento";

        //Mocks
        Film mockFilmTest = this.mockFilm.mockFilm();
        User mockUserTest = this.mockUser.mockUser();
        NotesAudience notesAudienceMock = new NotesAudience(ID_DEFAULT_FOR_TESTS, mockFilmTest, mockUserTest, NOTE_DEFAULT_FOR_TESTS, 5.4);

        // Stubs
        when(this.repository.addNoteByFilmTitle(NOTE_DEFAULT_FOR_TESTS, TITLE_FILM_DEFAULT_FOR_TESTS, EMAIL_DEFAULT_FOR_TESTS)).thenReturn(notesAudienceMock);

        var result = this.service.addNotesByFilmTitle(NOTE_DEFAULT_FOR_TESTS, TITLE_FILM_DEFAULT_FOR_TESTS, EMAIL_DEFAULT_FOR_TESTS);
        verify(this.repository, times(1)).addNoteByFilmTitle(NOTE_DEFAULT_FOR_TESTS, TITLE_FILM_DEFAULT_FOR_TESTS, EMAIL_DEFAULT_FOR_TESTS);
        assertInstanceOf(NotesResponseDTO.class, result);
    }


    @Test
    void testFindAllNotes() {
        final Double NOTE_DEFAULT_FOR_TESTS = 3.5;

        Film mockFilmTest = this.mockFilm.mockFilm();
        User mockUserTest = this.mockUser.mockUser();

        NotesAudience n1 = new NotesAudience(1L, mockFilmTest, mockUserTest, NOTE_DEFAULT_FOR_TESTS, 5.4);
        NotesAudience n2 = new NotesAudience(2L, mockFilmTest, mockUserTest, NOTE_DEFAULT_FOR_TESTS, 5.4);

        List<NotesAudience> mockAllNotes = List.of(n1, n2);
        when(this.repository.findAllNotes()).thenReturn(mockAllNotes);
        var result = this.service.findAllNotes();
        assertEquals(2, result.size());
    }


    @Test
    void testChangeNotesByFilmId() {
        var mockUserTest = mock(User.class);
        var mockFilmTest = mock(Film.class);
        var mockUserDTOTest = mock(UserDTO.class);

        NotesAudience notesAudienceMock = new NotesAudience(1L, mockFilmTest, mockUserTest, 7.5, 5.4);
        when(this.repository.changeNoteByFilmId(2.0, mockFilmTest.getId(), mockUserDTOTest.email() ,1L)).thenReturn(notesAudienceMock);
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
