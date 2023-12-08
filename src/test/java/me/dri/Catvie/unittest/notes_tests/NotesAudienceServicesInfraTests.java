package me.dri.Catvie.unittest.notes_tests;

import me.dri.Catvie.domain.exceptions.NotFoundFilm;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.models.entities.NotesAudience;
import me.dri.Catvie.domain.ports.repositories.NotesAudiencesPort;
import me.dri.Catvie.infra.adapters.NotesAudienceAdapter;
import me.dri.Catvie.infra.entities.FilmEntity;
import me.dri.Catvie.infra.entities.NotesAudienceEntity;
import me.dri.Catvie.infra.entities.UserEntity;
import me.dri.Catvie.infra.jpa.FilmRepositoryJPA;
import me.dri.Catvie.infra.jpa.NotesAudiencesRepositoryJPA;
import me.dri.Catvie.infra.jpa.UserRepositoryJPA;
import me.dri.Catvie.infra.ports.mappers.MapperFilmInfraPort;
import me.dri.Catvie.infra.ports.mappers.MapperUserInfraPort;
import me.dri.Catvie.unittest.mocks.MockFilm;
import me.dri.Catvie.unittest.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    MapperFilmInfraPort mapperFilmInfraPort;

    @Mock
    MapperUserInfraPort mapperUserInfraPort;


    NotesAudiencesPort service;


    MockFilm mockFilm;

    MockUser mockUser;


    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockFilm = new MockFilm();
        this.mockUser = new MockUser();
        this.service = new NotesAudienceAdapter(this.filmRepositoryJPA, this.notesAudiencesRepositoryJPA, this.userRepositoryJPA, this.mapperFilmInfraPort, this.mapperUserInfraPort);
    }

    @Test
    void testAddNotesByFilmId() {
        FilmEntity filmEntity = this.mockFilm.mockFilmEntity();
        UserEntity userEntity = this.mockUser.mockUserEntity();
        NotesAudienceEntity notesAudienceEntity = new NotesAudienceEntity(1L, filmEntity, userEntity, 5.6);
        when(this.notesAudiencesRepositoryJPA.findUserAlreadyRatedFilm(userEntity.getId(), filmEntity.getId())).thenReturn(Optional.empty());
        when(this.filmRepositoryJPA.findFilmById(filmEntity.getId())).thenReturn(Optional.of(filmEntity));
        when(this.userRepositoryJPA.findByEmail(userEntity.getEmail())).thenReturn(Optional.of(userEntity));
        when(this.notesAudiencesRepositoryJPA.save(notesAudienceEntity)).thenReturn(notesAudienceEntity);
        var result = this.service.addNoteByFilmId(3.5, filmEntity.getId(), userEntity.getEmail());
        verify(this.filmRepositoryJPA, times(1)).save(filmEntity);
        assertInstanceOf(NotesAudience.class, result);

    }


    @Test
    void testAddNotesByFilmTitle() {
        FilmEntity mockFilmEntity = this.mockFilm.mockFilmEntity();
        UserEntity mockUserEntity = this.mockUser.mockUserEntity();
        UserEntity user = this.mockUser.mockUserEntity();
        when(this.filmRepositoryJPA.findFilmByTitle(mockFilmEntity.getTitle())).thenReturn(Optional.of(mockFilmEntity));
        when(this.userRepositoryJPA.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        Film mockFilm = this.mockFilm.mockFilm();
        when(this.mapperFilmInfraPort.convertyFilmEntityToFilm(mockFilmEntity)).thenReturn(mockFilm);
        when(this.userRepositoryJPA.findById(1L)).thenReturn(Optional.of(mockUserEntity));
        var result = this.service.addNoteByFilmTitle(2.0, mockFilmEntity.getTitle(), user.getEmail());
        assertEquals("Evangelion", result.getFilm().getTitle());
    }

    @Test
    void testChangeNotesByFilmId() {
        FilmEntity mockFilmEntity = this.mockFilm.mockFilmEntity();
        UserEntity user = this.mockUser.mockUserEntity();
        NotesAudienceEntity notesAudienceEntity = new NotesAudienceEntity(1L, mockFilmEntity, user, 5.0);
        when(this.filmRepositoryJPA.findFilmById(mockFilmEntity.getId())).thenReturn(Optional.of(mockFilmEntity));
        when(this.userRepositoryJPA.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        Film mockFilm = this.mockFilm.mockFilm();
        when(this.mapperFilmInfraPort.convertyFilmEntityToFilm(mockFilmEntity)).thenReturn(mockFilm);
        when(this.userRepositoryJPA.findById(1L)).thenReturn(Optional.of(user));
        when(this.notesAudiencesRepositoryJPA.findUserAlreadyRatedFilm(user.getId(), user.getId())).thenReturn(Optional.of(user));
        when(this.notesAudiencesRepositoryJPA.findById(1L)).thenReturn(Optional.of(notesAudienceEntity));
        var result = this.service.changeNoteByFilmId(2.0, mockFilmEntity.getId(), user.getEmail(), 1L);
        assertEquals("Evangelion", result.getFilm().getTitle());
    }

    @Test
    void testAddNotesByFilmIdNotFound() {
        UserEntity user = this.mockUser.mockUserEntity();
        when(this.filmRepositoryJPA.findFilmById(1L)).thenReturn(Optional.empty());
        var exception = assertThrows(NotFoundFilm.class, () -> this.service.addNoteByFilmId(2.0, 1L, user.getEmail()));
        verify(this.mapperFilmInfraPort, times(0)).convertyFilmEntityToFilm(any());
        assertEquals("Film by id not found", exception.getMessage());

    }

    @Test
    void testAddNotesByFilmTitleNotFound() {
        UserEntity user = this.mockUser.mockUserEntity();
        when(this.filmRepositoryJPA.findFilmByTitle("Evangelion")).thenReturn(Optional.empty());
        var exception = assertThrows(NotFoundFilm.class, () -> this.service.addNoteByFilmTitle(2.0, "Evangelion", user.getEmail()));
        verify(this.mapperFilmInfraPort, times(0)).convertyFilmEntityToFilm(any());
        assertEquals("Not found film by title", exception.getMessage());

    }

//    @Test
//    void testChangeNotesByFilmIdNotFound() {
//        UserEntity user = this.mockUser.mockUserEntity();
//        FilmEntity film = this.mockFilm.mockFilmEntity();
//        when(this.filmRepositoryJPA.findFilmById(1L)).thenReturn(Optional.of(film));
//        when(this.userRepositoryJPA.findByEmail("diego@gmail.com")).thenReturn(Optional.of(user));
//        when(this.notesAudiencesRepositoryJPA.findUserAlreadyRatedFilm(1L, 1L)).thenReturn(Optional.empty());
//        var exception = assertThrows(FilmNotRated.class, () -> this.service.changeNoteByFilmId(2.0, 1L, user.getEmail()));
//        verify(this.mapperFilmInfraPort, times(0)).convertyFilmEntityToFilm(any());
//        assertEquals("User doesn't rated this film", exception.getMessage());

    //}


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
