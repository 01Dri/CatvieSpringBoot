package me.dri.Catvie.unittest.filmservicestest;

import me.dri.Catvie.domain.exceptions.InvalidGenre;
import me.dri.Catvie.domain.exceptions.NotFoundDirector;
import me.dri.Catvie.domain.exceptions.NotFoundFilm;
import me.dri.Catvie.domain.exceptions.user.NotFoundUser;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.infra.adapters.FilmAdapter;
import me.dri.Catvie.infra.entities.DirectorEntity;
import me.dri.Catvie.infra.entities.FilmEntity;
import me.dri.Catvie.infra.entities.GenreEntity;
import me.dri.Catvie.infra.entities.UserEntity;
import me.dri.Catvie.infra.jpa.*;
import me.dri.Catvie.unittest.mocks.MockDirector;
import me.dri.Catvie.unittest.mocks.MockFilm;
import me.dri.Catvie.unittest.mocks.MockGenre;
import me.dri.Catvie.unittest.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

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
    NotesAudiencesRepositoryJPA notesAudiencesPort;

    @Mock
    UserRepositoryJPA userRepositoryJPA;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    FilmAdapter service;

    MockFilm mockFilm;

    MockGenre mockGenre;

    MockDirector mockDirector;

    MockUser mockUser;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockFilm = new MockFilm();
        mockDirector =new MockDirector(mockFilm);
        mockUser = new MockUser();
        mockGenre = new MockGenre();
    }


    @Test
    void testCreateFilm() {
        // Mocks
        FilmEntity mockFilmEntity = this.mockFilm.mockFilmEntity();
        GenreEntity mockGenreEntity = this.mockGenre.mockGenreEntity();
        DirectorEntity mockDirectorEntity = this.mockDirector.mockDirectorEntity();
        UserEntity mockUserEntity = this.mockUser.mockUserEntity();;
        Film mockFilm = this.mockFilm.mockFilm();
        String subjectEmail = "diego@gmail.com";

        when(this.genreRepositoryJPA.findBygenreName(any())).thenReturn(Optional.of(mockGenreEntity));
        when(this.directorRepositoryJPA.findByName(mockFilm.getDirector().getName())).thenReturn(Optional.of(mockDirectorEntity));
        when(this.userRepositoryJPA.findByEmail(subjectEmail)).thenReturn(Optional.of(mockUserEntity));
        when(this.modelMapper.map(mockFilm, FilmEntity.class)).thenReturn(mockFilmEntity);
        when(this.modelMapper.map(mockFilmEntity, Film.class)).thenReturn(mockFilm);
        var result = this.service.create(mockFilm, subjectEmail);
        assertEquals(mockFilm.getId(), result.getId());
        assertEquals(mockFilm.getTitle(), result.getTitle());
        assertEquals(mockFilm.getGenres(), result.getGenres());
        assertEquals(mockFilm.getDirector(), result.getDirector());
        assertEquals(mockFilm.getUser(), result.getUser());
        verify(this.filmRepositoryJPA, times(1)).save(mockFilmEntity);


    }

    @Test
    void testErrorNotFoundDirectorCreateFilm() {
        Film mockFilm = this.mockFilm.mockFilm();
        GenreEntity mockGenreEntity = this.mockGenre.mockGenreEntity();
        FilmEntity mockFilmEntity = this.mockFilm.mockFilmEntity();
        String subjectTest = "diego@gmail.com";
        when(this.genreRepositoryJPA.findBygenreName(any())).thenReturn(Optional.of(mockGenreEntity));
        when(this.directorRepositoryJPA.findByName(mockFilm.getDirector().getName())).thenReturn(Optional.empty());
        var exception = assertThrows(NotFoundDirector.class, () -> this.service.create(mockFilm, subjectTest));
        assertEquals("Director not found", exception.getMessage());
        verify(this.filmRepositoryJPA, times(0)).save(mockFilmEntity);

    }

    @Test
    void testErrorNotFoundGenreCreateFilm() {
        Film mockFilm = this.mockFilm.mockFilm();
        FilmEntity mockFilmEntity = this.mockFilm.mockFilmEntity();
        String subjectTest = "diego@gmail.com";
        when(this.genreRepositoryJPA.findBygenreName(any())).thenReturn(Optional.empty());
        var exception = assertThrows(InvalidGenre.class, () -> this.service.create(mockFilm, subjectTest));
        assertEquals("The genre was not found", exception.getMessage());
        verify(this.filmRepositoryJPA, times(0)).save(mockFilmEntity);

    }

    @Test
    void testErrorNotFoundUserBySubjectCreateFilm() {
        Film mockFilm = this.mockFilm.mockFilm();
        GenreEntity mockGenres = this.mockGenre.mockGenreEntity();
        DirectorEntity mockDirector = this.mockDirector.mockDirectorEntity();
        FilmEntity mockFilmEntity = this.mockFilm.mockFilmEntity();
        String subjectTest = "diego@gmail.com";

        when(this.genreRepositoryJPA.findBygenreName(any())).thenReturn(Optional.of(mockGenres));
        when(this.directorRepositoryJPA.findByName(mockFilm.getDirector().getName())).thenReturn(Optional.of(mockDirector));
        when(this.userRepositoryJPA.findByEmail(subjectTest)).thenReturn(Optional.empty());

        var exception = assertThrows(NotFoundUser.class, () -> this.service.create(mockFilm, subjectTest));

        assertEquals("User not found", exception.getMessage());
        verify(this.filmRepositoryJPA, times(0)).save(mockFilmEntity);

    }
    
    @Test
    void testFindById() {
        Long ID_DEFAULT_FOR_TESTS = 1L;
        FilmEntity mockFilmEntity = this.mockFilm.mockFilmEntity();
        Film mockFilm = this.mockFilm.mockFilm();
        when(this.filmRepositoryJPA.findFilmById(ID_DEFAULT_FOR_TESTS)).thenReturn(Optional.of(mockFilmEntity));
        when(this.modelMapper.map(mockFilmEntity, Film.class)).thenReturn(mockFilm);
        var result = this.service.findById(ID_DEFAULT_FOR_TESTS);
        verify(this.filmRepositoryJPA, times(1)).findFilmById(ID_DEFAULT_FOR_TESTS);
        assertEquals(mockFilmEntity.getTitle(), result.getTitle());

    }

    @Test
    void testFindByTitle() {
        String FILM_TITLE_DEFAULT_FOR_TESTS = "FilmTesteTitle";
        FilmEntity mockFilmEntity = this.mockFilm.mockFilmEntity();
        Film mockFilm = this.mockFilm.mockFilm();
        when(this.filmRepositoryJPA.findFilmByTitle(FILM_TITLE_DEFAULT_FOR_TESTS)).thenReturn(Optional.of(mockFilmEntity));
        when(this.modelMapper.map(mockFilmEntity, Film.class)).thenReturn(mockFilm);
        var result = this.service.findByTitle(FILM_TITLE_DEFAULT_FOR_TESTS);
        verify(this.filmRepositoryJPA, times(1)).findFilmByTitle(FILM_TITLE_DEFAULT_FOR_TESTS);
        assertEquals(mockFilmEntity.getTitle(), result.getTitle());
    }

    @Test
    void testNotFoundFilmById() {
        when(this.filmRepositoryJPA.findFilmById(1L)).thenReturn(Optional.empty());
        var exception = assertThrows(NotFoundFilm.class, () -> this.service.findById(1L));
        verify(this.filmRepositoryJPA, times(1)).findFilmById(1L);
        assertEquals("Film with this id: " + 1L + " does not exist!" , exception.getMessage());
    }

    @Test
    void testNotFoundFilmByTitle() {
        when(this.filmRepositoryJPA.findFilmByTitle("Filme")).thenReturn(Optional.empty());
        var exception = assertThrows(NotFoundFilm.class, () -> this.service.findByTitle("Filme"));
        verify(this.filmRepositoryJPA, times(1)).findFilmByTitle(any());
        assertEquals("Film with this title: " + "Filme" + " Does not exist!", exception.getMessage());

    }

    @Test
    void testFindAll() {
        var listFilmsEntityMock = this.mockFilm.mockListFilmsEntity();
        var listFilmsMock = this.mockFilm.mockListFilms();
        when(this.filmRepositoryJPA.findAllFilms()).thenReturn(Optional.of(listFilmsEntityMock));
        var result = this.service.findAllFilmEntity();
        verify(this.filmRepositoryJPA, times(1)).findAllFilms();
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
