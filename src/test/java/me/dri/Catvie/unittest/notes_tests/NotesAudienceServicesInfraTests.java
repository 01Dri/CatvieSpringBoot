package me.dri.Catvie.unittest.notes_tests;

import me.dri.Catvie.domain.exceptions.NotFoundFilm;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.models.entities.NotesAudience;
import me.dri.Catvie.domain.models.entities.User;
import me.dri.Catvie.infra.adapters.NotesAudienceAdapter;
import me.dri.Catvie.infra.entities.FilmEntity;
import me.dri.Catvie.infra.entities.NotesAudienceEntity;
import me.dri.Catvie.infra.entities.UserEntity;
import me.dri.Catvie.infra.jpa.FilmRepositoryJPA;
import me.dri.Catvie.infra.jpa.NotesAudiencesRepositoryJPA;
import me.dri.Catvie.infra.jpa.UserRepositoryJPA;
import me.dri.Catvie.unittest.mocks.MockFilm;
import me.dri.Catvie.unittest.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class NotesAudienceServicesInfraTests {


    @Mock
    FilmRepositoryJPA filmRepositoryJPA;

    @Mock
    NotesAudiencesRepositoryJPA notesAudiencesRepositoryJPA;

    @Mock
    UserRepositoryJPA userRepositoryJPA;

    @Mock
    ModelMapper modelMapper;


    @InjectMocks
    NotesAudienceAdapter service;


    MockFilm mockFilm;

    MockUser mockUser;


    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockFilm = new MockFilm();
        this.mockUser = new MockUser();
    }

    @Test
    void testAddNotesByFilmId() {
        // Constants
        final Long FILM_ID_DEFAULT_FOR_TESTS = 1L;
        final String EMAIL_DEFAULT_FOR_TEST = "diego@gmail.com";

        // Mocks
        FilmEntity mockFilmEntity = this.mockFilm.mockFilmEntity();
        Film mockFilmReturned = this.mockFilm.mockFilm();
        UserEntity mockUserEntity = this.mockUser.mockUserEntity();
        User mockUserReturned = this.mockUser.mockUser();
        NotesAudienceEntity mockNotesEntity = new NotesAudienceEntity(1L, mockFilmEntity, mockUserEntity, 5.3);

        //Stubs
        when(this.filmRepositoryJPA.findFilmById(FILM_ID_DEFAULT_FOR_TESTS)).thenReturn(Optional.of(mockFilmEntity));
        when(this.userRepositoryJPA.findByEmail(EMAIL_DEFAULT_FOR_TEST)).thenReturn(Optional.of(mockUserEntity));
        when(this.notesAudiencesRepositoryJPA.save(any(NotesAudienceEntity.class))).thenReturn(mockNotesEntity); // It does not work
        when(this.modelMapper.map(mockFilmEntity, Film.class)).thenReturn(mockFilmReturned);
        when(this.modelMapper.map(mockUserReturned, User.class)).thenReturn(mockUserReturned);


        var result = this.service.addNoteByFilmId(5.3, FILM_ID_DEFAULT_FOR_TESTS, EMAIL_DEFAULT_FOR_TEST);
        verify(this.filmRepositoryJPA, times(1)).save(mockFilmEntity);
        assertInstanceOf(NotesAudience.class, result);
        assertEquals(result.getFilm().getTitle(), mockFilmEntity.getTitle());
    }


    @Test
    void testAddNotesByFilmTitle() {

        // Constants
        final String EMAIL_DEFAULT_FOR_TEST = "diego@gmail.com";
        final String TITLE_FILM_DEFAULT_FOR_TEST = "O Convento";

        // Mocks Entities for tests
        FilmEntity mockFilmEntity = this.mockFilm.mockFilmEntity();
        Film mockFilmReturned = this.mockFilm.mockFilm();
        UserEntity mockUserEntity = this.mockUser.mockUserEntity();
        User mockUserReturned = this.mockUser.mockUser();
        NotesAudienceEntity mockNotesEntity = new NotesAudienceEntity(1L, mockFilmEntity, mockUserEntity, 5.3);

        // Stubs
        when(this.filmRepositoryJPA.findFilmByTitle(TITLE_FILM_DEFAULT_FOR_TEST)).thenReturn(Optional.of(mockFilmEntity));
        when(this.userRepositoryJPA.findByEmail(EMAIL_DEFAULT_FOR_TEST)).thenReturn(Optional.of(mockUserEntity));
        when(this.notesAudiencesRepositoryJPA.save(any(NotesAudienceEntity.class))).thenReturn(mockNotesEntity); //Not working
        when(this.modelMapper.map(mockFilmEntity, Film.class)).thenReturn(mockFilmReturned);
        when(this.modelMapper.map(mockUserReturned, User.class)).thenReturn(mockUserReturned);

        var result = this.service.addNoteByFilmTitle(3.5, TITLE_FILM_DEFAULT_FOR_TEST, EMAIL_DEFAULT_FOR_TEST);
        verify(this.filmRepositoryJPA, times(1)).save(mockFilmEntity);
        assertInstanceOf(NotesAudience.class, result);
        assertEquals(result.getFilm().getTitle(), mockFilmEntity.getTitle());
    }

    @Test
    void testChangeNotesByFilmId() {
        // Constants
        final Long NOTE_ID_DEFAULT_FOR_TESTS = 1L;
        final Long FILM_ID_DEFAULT_FOR_TESTS = 1L;
        final Long USER_ID_DEFAULT_FOR_TESTS = 1L;
        final String EMAIL_DEFAULT_FOR_TEST = "diego@gmail.com";


        // Mocks Entities for tests
        FilmEntity mockFilmEntity = this.mockFilm.mockFilmEntity();
        Film mockFilmReturned = this.mockFilm.mockFilm();
        UserEntity mockUserEntity = this.mockUser.mockUserEntity();
        User mockUserReturned = this.mockUser.mockUser();
        NotesAudienceEntity mockNotesEntity = new NotesAudienceEntity(1L, mockFilmEntity, mockUserEntity, 5.3);

        //Stubs
        when(this.filmRepositoryJPA.findFilmById(FILM_ID_DEFAULT_FOR_TESTS)).thenReturn(Optional.of(mockFilmEntity));
        when(this.userRepositoryJPA.findByEmail(EMAIL_DEFAULT_FOR_TEST)).thenReturn(Optional.of(mockUserEntity));
        when(this.notesAudiencesRepositoryJPA.findUserAlreadyRatedFilm(USER_ID_DEFAULT_FOR_TESTS, FILM_ID_DEFAULT_FOR_TESTS)).thenReturn(Optional.of(mockUserEntity));
        when(this.notesAudiencesRepositoryJPA.findById(NOTE_ID_DEFAULT_FOR_TESTS)).thenReturn(Optional.of(mockNotesEntity));
        when(this.modelMapper.map(mockFilmEntity, Film.class)).thenReturn(mockFilmReturned);
        when(this.modelMapper.map(mockUserReturned, User.class)).thenReturn(mockUserReturned);


        var result = this.service.changeNoteByFilmId(2.0, FILM_ID_DEFAULT_FOR_TESTS, EMAIL_DEFAULT_FOR_TEST, NOTE_ID_DEFAULT_FOR_TESTS);
        assertEquals(2.0 , result.getNote());

    }

    @Test
    void testAddNotesByFilmIdNotFound() {
        UserEntity user = this.mockUser.mockUserEntity();
        when(this.filmRepositoryJPA.findFilmById(1L)).thenReturn(Optional.empty());

        var exception = assertThrows(NotFoundFilm.class, () -> this.service.addNoteByFilmId(2.0, 1L, user.getEmail()));
        verify(this.modelMapper, times(0)).map(any(), any());
        assertEquals("Film by id not found", exception.getMessage());

    }

    @Test
    void testAddNotesByFilmTitleNotFound() {
        UserEntity user = this.mockUser.mockUserEntity();

        when(this.filmRepositoryJPA.findFilmByTitle("Evangelion")).thenReturn(Optional.empty());

        var exception = assertThrows(NotFoundFilm.class, () -> this.service.addNoteByFilmTitle(2.0, "Evangelion", user.getEmail()));

        verify(this.modelMapper, times(0)).map(any(), any());
        assertEquals("Not found film by title", exception.getMessage());

    }


    @Test
    void testFindAllNotes() {
        FilmEntity mockFilmEntity = this.mockFilm.mockFilmEntity();
        UserEntity mockUserEntity = this.mockUser.mockUserEntity();
        NotesAudienceEntity mockN1 = new NotesAudienceEntity(1L, mockFilmEntity, mockUserEntity, 5.3);
        NotesAudienceEntity mockN2 = new NotesAudienceEntity(2L, mockFilmEntity, mockUserEntity, 5.3);
        List<NotesAudienceEntity> mockListNotesAudience = List.of(mockN1, mockN2);
        Film mockFilmReturned = this.mockFilm.mockFilm();
        User mockUserReturned = this.mockUser.mockUser();

        when(this.notesAudiencesRepositoryJPA.findAllNotes()).thenReturn(mockListNotesAudience);
        when(this.modelMapper.map(mockFilmEntity, Film.class)).thenReturn(mockFilmReturned);
        when(this.modelMapper.map(mockUserEntity, User.class)).thenReturn(mockUserReturned);

        var result = this.service.findAllNotes();
        assertEquals(2, result.size());
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
