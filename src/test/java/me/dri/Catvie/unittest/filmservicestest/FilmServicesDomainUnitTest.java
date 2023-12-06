package me.dri.Catvie.unittest.filmservicestest;

import me.dri.Catvie.domain.adapters.services.director.DirectorServiceImpl;
import me.dri.Catvie.domain.adapters.services.film.FilmServiceImpl;
import me.dri.Catvie.domain.adapters.services.genre.GenreServiceImpl;
import me.dri.Catvie.domain.exceptions.NotFoundFilm;
import me.dri.Catvie.domain.exceptions.film.*;
import me.dri.Catvie.domain.models.dto.film.FilmDTO;
import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.ports.interfaces.director.DirectorServicePort;
import me.dri.Catvie.domain.ports.interfaces.film.FilmServicePort;
import me.dri.Catvie.domain.ports.interfaces.genre.GenreServicesPort;
import me.dri.Catvie.domain.ports.interfaces.mappers.MapperFilmDomainPort;
import me.dri.Catvie.domain.ports.interfaces.mappers.MapperUserDomainPort;
import me.dri.Catvie.domain.ports.interfaces.user.UserServicePort;
import me.dri.Catvie.domain.ports.repositories.DirectorRepositoryPort;
import me.dri.Catvie.domain.ports.repositories.FilmRepositoryPort;
import me.dri.Catvie.domain.ports.repositories.GenreRepositoryPort;
import me.dri.Catvie.unittest.mocks.MockDirector;
import me.dri.Catvie.unittest.mocks.MockFilm;
import me.dri.Catvie.unittest.mocks.MockGenre;
import me.dri.Catvie.unittest.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FilmServicesDomainUnitTest {

    @Mock
    FilmRepositoryPort repository;
    @Mock
    MapperFilmDomainPort mapperPort;
    @Mock
    DirectorServicePort directorServicePort;
    @Mock
    GenreRepositoryPort genreRepositoryPort;
    @Mock
    DirectorRepositoryPort directorRepositoryPort;
    @Mock
    UserServicePort userServicePort;
    @Mock
    MapperUserDomainPort mapperUserDomainPort;

    GenreServicesPort genreServicesPort;
    FilmServicePort service;

    MockFilm mocksFilms;
    MockGenre mockGenre;

    MockDirector mockDirector;

    MockUser mockUser;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        genreServicesPort = new GenreServiceImpl(genreRepositoryPort);
        directorServicePort = new DirectorServiceImpl(directorRepositoryPort);
        service = new FilmServiceImpl(repository, mapperPort, genreServicesPort, directorServicePort, userServicePort,mapperUserDomainPort);
        this.mocksFilms = new MockFilm();
        this.mockGenre = new MockGenre();
        this.mockDirector = new MockDirector(mocksFilms);
        this.mockUser = new MockUser();
    }


    @Test
    void testCreateFilm() {

        FilmDTO mockFilmDTO = this.mocksFilms.mockFilmDto();
        Film mockFilm = this.mocksFilms.mockFilm();
        String subjectByToken  = "diego@gmail.com";
        Film mockFilmByInfra = this.mocksFilms.mockFilm();
        FilmResponseDTO mockFilmResponseDTO = this.mocksFilms.mockFilmResponseDTO();
        when(this.mapperPort.convertFilmDtoToFilm(mockFilmDTO, mockFilmDTO.genres(), mockFilmDTO.director())).thenReturn(mockFilm);
        when(this.repository.create(mockFilm, subjectByToken)).thenReturn(mockFilmByInfra);
        when(this.mapperPort.convertFilmToResponseDTO(mockFilmByInfra)).thenReturn(mockFilmResponseDTO);
        var result = this.service.create(mockFilmDTO, subjectByToken);
        assertInstanceOf(FilmResponseDTO.class, result);


    }


    @Test
    void testFindFilmByTitle() {
        Film film = mock(Film.class);
        FilmResponseDTO filmDto = mock(FilmResponseDTO.class);
        when(this.repository.findByTitle("film test 1")).thenReturn(film);
        when(this.mapperPort.convertFilmToResponseDTO(any())).thenReturn(filmDto);
        var result = this.service.findByTitle("film test 1");
        assertInstanceOf(FilmResponseDTO.class, result);
        verify(this.mapperPort, times(1)).convertFilmToResponseDTO(any());
    }

    @Test
    void testFindFilmById() {
        Film film = mock(Film.class);
        FilmResponseDTO filmDTO = mock(FilmResponseDTO.class);
        when(this.repository.findById(1L)).thenReturn(film);
        when(this.mapperPort.convertFilmToResponseDTO(any())).thenReturn(filmDTO);
        var result = this.service.findById(1L);
        System.out.println(result);
        assertEquals(result, filmDTO);
        verify(this.repository, times(1)).findById(any());
    }

    @Test
    void testFindAll() {
        List<Film> films = Collections.singletonList(mock(Film.class));
        List<FilmResponseDTO> filmDtos = Collections.singletonList(mock(FilmResponseDTO.class));
        when(this.repository.findAllFilmEntity()).thenReturn(films);
        when(this.mapperPort.convertListFilmToListDto(any())).thenReturn(filmDtos);
        var result = this.service.findAll();
        // Checking if result this a ListDto
        assertTrue(result instanceof List<FilmResponseDTO>);
        verify(this.mapperPort, times(1)).convertListFilmToListDto(any());
    }


    @Test
    void testNotFoundFilmById() {
        when(this.repository.findById(1L)).thenReturn(null);
        assertThrows(NotFoundFilm.class, () -> this.service.findById(1L));

    }

    @Test
    void testNotFoundFilmByTitle() {
        String nameInvalid = "isso aqui não existe fio";
        when(this.repository.findByTitle(nameInvalid)).thenReturn(null);
        assertThrows(NotFoundFilm.class, () -> this.service.findByTitle(nameInvalid));

    }

    @Test
    void testExceptionCreateFilmWithTitleEmpty() {
        var filmDto = this.mocksFilms.mockFilmDtoTitleEmpty();
        var exception = assertThrows(InvalidTitleFilmException.class, () -> this.service.create(filmDto, "diego@gmail.com"));
        assertEquals("Content 'title' is empty", exception.getMessage());
    }
    @Test
    void testExceptionCreateFilmWithTitleNull() {
        var filmDto = this.mocksFilms.mockFilmDtoTitleNull();
        var exception = assertThrows(InvalidTitleFilmException.class, () -> this.service.create(filmDto, "diego@gmail.com"));
        assertEquals("Content 'title' is null", exception.getMessage());
    }

    @Test
    void testExceptionCreateFilmWithLanguageEmpty() {
        var filmDto = this.mocksFilms.mockFilmDtoLanguageEmpty();
        var exception = assertThrows(InvalidLanguageFilmException.class, () -> this.service.create(filmDto, "diego@gmail.com"));
        assertEquals("Content 'original_language' is empty", exception.getMessage());
    }
    @Test
    void testExceptionCreateFilmWithLanguageNull() {
        var filmDto = this.mocksFilms.mockFilmDtoLanguageNull();
        var exception = assertThrows(InvalidLanguageFilmException.class, () -> this.service.create(filmDto, "diego@gmail.com"));
        assertEquals("Content 'original_language' is null", exception.getMessage());
    }

    @Test
    void testExceptionCreateFilmWithWriterEmpty() {
        var filmDto = this.mocksFilms.mockFilmDtoWriterEmpty();
        var exception = assertThrows(InvalidWriterFilmException.class, () -> this.service.create(filmDto, "diego@gmail.com"));
        assertEquals("Content 'writer' is empty", exception.getMessage());
    }
    @Test
    void testExceptionCreateFilmWithWriterNull() {
        var filmDto = this.mocksFilms.mockFilmDtoWriterNull();
        var exception = assertThrows(InvalidWriterFilmException.class, () -> this.service.create(filmDto, "diego@gmail.com"));
        assertEquals("Content 'writer' is null", exception.getMessage());
    }

    @Test
    void testExceptionCreateFilmWithDistributorEmpty() {
        var filmDto = this.mocksFilms.mockFilmDtoDistributorEmpty();
        var exception = assertThrows(InvalidDistributorFilmException.class, () -> this.service.create(filmDto, "diego@gmail.com"));
        assertEquals("Content 'distributor' is empty", exception.getMessage());
    }
    @Test
    void testExceptionCreateFilmWithDistributorNull() {
        var filmDto = this.mocksFilms.mockFilmDtoDistributorNull();
        var exception = assertThrows(InvalidDistributorFilmException.class, () -> this.service.create(filmDto, "diego@gmail.com"));
        assertEquals("Content 'distributor' is null", exception.getMessage());
    }

    @Test
    void testExceptionCreateFilmWithProductionEmpty() {
        var filmDto = this.mocksFilms.mockFilmDtoProductionEmpty();
        var exception = assertThrows(InvalidProdutionFilmException.class, () -> this.service.create(filmDto, "diego@gmail.com"));
        assertEquals("Content 'prodution' is empty", exception.getMessage());
    }
    @Test
    void testExceptionCreateFilmWithProductionNull() {
        var filmDto = this.mocksFilms.mockFilmDtoProductionNully();
        var exception = assertThrows(InvalidProdutionFilmException.class, () -> this.service.create(filmDto, "diego@gmail.com"));
        assertEquals("Content 'prodution' is null", exception.getMessage());
    }

    @Test
    void testExceptionCreateFilmWithUrlEmpty() {
        var filmDto = this.mocksFilms.mockFilmDtoUrlEmpty();
        var exception = assertThrows(InvalidUrlImageFilmException.class, () -> this.service.create(filmDto, "diego@gmail.com"));
        assertEquals("Content 'url' is empty", exception.getMessage());
    }

    @Test
    void testExceptionCreateFilmWithDUrlNull() {
        var filmDto = this.mocksFilms.mockFilmDtoUrlNull();
        var exception = assertThrows(InvalidUrlImageFilmException.class, () -> this.service.create(filmDto, "diego@gmail.com"));
        assertEquals("Content 'url' is null", exception.getMessage());
    }

    @Test
    void testExceptionCreateFilmWithRuntimeNull() {
        var filmDto = this.mocksFilms.mockFilmDtoRuntimeNull();
        var exception = assertThrows(InvalidRuntimeFilmException.class, () -> this.service.create(filmDto, "diego@gmail.com"));
        assertEquals("Content 'runtime' is null", exception.getMessage());
    }

    @Test
    void testExceptionCreateFilmWithReleaseDateNull() {
        var filmDto = this.mocksFilms.mockFilmDtoReleaseDateNull();
        var exception = assertThrows(InvalidReleaseDateFilmException.class, () -> this.service.create(filmDto, "diego@gmail.com"));
        assertEquals("Content 'release_date' is null", exception.getMessage());

    }


    }
