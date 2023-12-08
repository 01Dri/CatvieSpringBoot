package me.dri.Catvie.unittest.filmservicestest;

import me.dri.Catvie.domain.adapters.services.film.FilmServiceImpl;
import me.dri.Catvie.domain.adapters.services.genre.GenreServiceImpl;
import me.dri.Catvie.domain.exceptions.NotFoundFilm;
import me.dri.Catvie.domain.exceptions.film.*;
import me.dri.Catvie.domain.models.dto.film.FilmRequestDTO;
import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;
import me.dri.Catvie.domain.models.entities.Director;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.models.entities.Genre;
import me.dri.Catvie.domain.ports.interfaces.genre.GenreServicesPort;
import me.dri.Catvie.domain.ports.interfaces.mappers.MapperFilmResponsePort;
import me.dri.Catvie.domain.ports.interfaces.mappers.MapperUserResponsePort;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FilmServicesDomainUnitTest {

    @Mock
    FilmRepositoryPort repository;
    @Mock
    MapperFilmResponsePort mapperFilmResponse;
    @Mock
    GenreRepositoryPort genreRepositoryPort;
    @Mock
    DirectorRepositoryPort directorRepositoryPort;
    @Mock
    UserServicePort userServicePort;
    @Mock
    MapperUserResponsePort mapperUserResponse;

    @Mock
    ModelMapper modelMapper;

    @Mock
    GenreServiceImpl genreServicesPort;

    FilmServiceImpl service;

    MockFilm mocksFilms;
    MockGenre mockGenre;

    MockDirector mockDirector;

    MockUser mockUser;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.service = new FilmServiceImpl(this.repository, this.mapperFilmResponse, this.genreServicesPort, this.directorRepositoryPort, this.modelMapper);
        this.mocksFilms = new MockFilm();
        this.mockGenre = new MockGenre();
        this.mockDirector = new MockDirector(mocksFilms);
        this.mockUser = new MockUser();
    }


    @Test
    void testCreateFilm() {

        final String SUBJECT_EMAIl_DEFAULT_FOR_TESTS = "diego@gmail.com";

        FilmRequestDTO mockFilmRequestDTO = this.mocksFilms.mockFilmRequestDTO();
        Genre genreByInfraAdapter = this.mockGenre.mockGenre();
        Set<Genre> mockSetGenreByInfraForReturn = this.mockGenre.mockSetGenres();
        Director mockDirectorByInfraForReturn = this.mockDirector.mockDirector();;
        Film mockFilmConvertedByModelMapper = this.mocksFilms.mockFilm();
        FilmResponseDTO mockFilmConvertedByMapperResponse = this.mocksFilms.mockFilmResponseDTO();
        Film mockTest = this.mocksFilms.mockFilm();

        when(this.genreRepositoryPort.findByName(any())).thenReturn(genreByInfraAdapter);
        when(this.genreServicesPort.verifyExistingGenres(mockFilmRequestDTO.getGenres())).thenReturn(mockSetGenreByInfraForReturn);
        when(this.directorRepositoryPort.findByName(mockFilmRequestDTO.getDirector().getName())).thenReturn(mockDirectorByInfraForReturn);
        when(this.modelMapper.map(mockFilmRequestDTO, Film.class)).thenReturn(mockFilmConvertedByModelMapper);
        when(this.repository.create(mockFilmConvertedByModelMapper, SUBJECT_EMAIl_DEFAULT_FOR_TESTS)).thenReturn(mockTest);
        when(this.mapperFilmResponse.convertFilmToResponseDTO(mockTest)).thenReturn(mockFilmConvertedByMapperResponse);
        FilmResponseDTO result = this.service.create(mockFilmRequestDTO, SUBJECT_EMAIl_DEFAULT_FOR_TESTS);

        verify(this.mapperFilmResponse, times(1)).convertFilmToResponseDTO(mockFilmConvertedByModelMapper);
        assertEquals(mockTest.getTitle(), result.title());

    }

    @Test
    void testFindFilmByTitle() {
        final String TITLE_FILM_FOR_TESTS = "film teste 1";
        Film mockFilmByInfraForReturn = this.mocksFilms.mockFilm();
        FilmResponseDTO mockFilmResponseDtoForReturn = this.mocksFilms.mockFilmResponseDTO();
        when(this.repository.findByTitle(TITLE_FILM_FOR_TESTS)).thenReturn(mockFilmByInfraForReturn);
        when(this.mapperFilmResponse.convertFilmToResponseDTO(mockFilmByInfraForReturn)).
                thenReturn(mockFilmResponseDtoForReturn);
        var result = this.service.findByTitle(TITLE_FILM_FOR_TESTS);
        assertInstanceOf(FilmResponseDTO.class, result);
        verify(this.mapperFilmResponse, times(1)).convertFilmToResponseDTO(mockFilmByInfraForReturn);
    }

    @Test
    void testFindFilmById() {
        Long ID_DEFAULT_FILM_FOR_TESTS = 1L;
        Film mockFilmByInfraForReturn = mock(Film.class);
        FilmResponseDTO mockFilmResponseDTOForReturn = mock(FilmResponseDTO.class);
        when(this.repository.findById(ID_DEFAULT_FILM_FOR_TESTS)).thenReturn(mockFilmByInfraForReturn);
        when(this.mapperFilmResponse.convertFilmToResponseDTO(mockFilmByInfraForReturn)).thenReturn(mockFilmResponseDTOForReturn);
        var result = this.service.findById(ID_DEFAULT_FILM_FOR_TESTS);
        assertInstanceOf(FilmResponseDTO.class, result);
        verify(this.repository, times(1)).findById(ID_DEFAULT_FILM_FOR_TESTS);
    }

    @Test
    void testFindAll() {
        List<Film> mockListFilmByInfraAdapterForReturn = this.mocksFilms.mockListFilms();
        List<FilmResponseDTO> mockListFilmsResponseDTO = this.mocksFilms.mockListFilmsResponseDTO();
        when(this.repository.findAllFilmEntity()).thenReturn(mockListFilmByInfraAdapterForReturn);
        when(this.mapperFilmResponse.convertListFilmToFilmResponseDTOList(mockListFilmByInfraAdapterForReturn)).thenReturn(mockListFilmsResponseDTO);
        var result = this.service.findAll();
        assertEquals(mockListFilmsResponseDTO.size(), result.size());
    }



    @Test
    void testExceptionCreateFilmWithTitleEmpty() {
        var FilmRequestDTO = this.mocksFilms.mockFilmRequestDTOTitleEmpty();
        var exception = assertThrows(InvalidTitleFilmException.class, () -> this.service.create(FilmRequestDTO, "diego@gmail.com"));
        assertEquals("Content 'title' is empty", exception.getMessage());
    }
    @Test
    void testExceptionCreateFilmWithTitleNull() {
        var FilmRequestDTO = this.mocksFilms.mockFilmRequestDTOTitleNull();
        var exception = assertThrows(InvalidTitleFilmException.class, () -> this.service.create(FilmRequestDTO, "diego@gmail.com"));
        assertEquals("Content 'title' is null", exception.getMessage());
    }

    @Test
    void testExceptionCreateFilmWithLanguageEmpty() {
        var FilmRequestDTO = this.mocksFilms.mockFilmRequestDTOLanguageEmpty();
        var exception = assertThrows(InvalidLanguageFilmException.class, () -> this.service.create(FilmRequestDTO, "diego@gmail.com"));
        assertEquals("Content 'original_language' is empty", exception.getMessage());
    }
    @Test
    void testExceptionCreateFilmWithLanguageNull() {
        var FilmRequestDTO = this.mocksFilms.mockFilmRequestDTOLanguageNull();
        var exception = assertThrows(InvalidLanguageFilmException.class, () -> this.service.create(FilmRequestDTO, "diego@gmail.com"));
        assertEquals("Content 'original_language' is null", exception.getMessage());
    }

    @Test
    void testExceptionCreateFilmWithWriterEmpty() {
        var FilmRequestDTO = this.mocksFilms.mockFilmRequestDTOWriterEmpty();
        var exception = assertThrows(InvalidWriterFilmException.class, () -> this.service.create(FilmRequestDTO, "diego@gmail.com"));
        assertEquals("Content 'writer' is empty", exception.getMessage());
    }
    @Test
    void testExceptionCreateFilmWithWriterNull() {
        var FilmRequestDTO = this.mocksFilms.mockFilmRequestDTOWriterNull();
        var exception = assertThrows(InvalidWriterFilmException.class, () -> this.service.create(FilmRequestDTO, "diego@gmail.com"));
        assertEquals("Content 'writer' is null", exception.getMessage());
    }

    @Test
    void testExceptionCreateFilmWithDistributorEmpty() {
        var FilmRequestDTO = this.mocksFilms.mockFilmRequestDTODistributorEmpty();
        var exception = assertThrows(InvalidDistributorFilmException.class, () -> this.service.create(FilmRequestDTO, "diego@gmail.com"));
        assertEquals("Content 'distributor' is empty", exception.getMessage());
    }
    @Test
    void testExceptionCreateFilmWithDistributorNull() {
        var FilmRequestDTO = this.mocksFilms.mockFilmRequestDTODistributorNull();
        var exception = assertThrows(InvalidDistributorFilmException.class, () -> this.service.create(FilmRequestDTO, "diego@gmail.com"));
        assertEquals("Content 'distributor' is null", exception.getMessage());
    }

    @Test
    void testExceptionCreateFilmWithProductionEmpty() {
        var FilmRequestDTO = this.mocksFilms.mockFilmRequestDTOProductionEmpty();
        var exception = assertThrows(InvalidProdutionFilmException.class, () -> this.service.create(FilmRequestDTO, "diego@gmail.com"));
        assertEquals("Content 'prodution' is empty", exception.getMessage());
    }
    @Test
    void testExceptionCreateFilmWithProductionNull() {
        var FilmRequestDTO = this.mocksFilms.mockFilmRequestDTOProductionNully();
        var exception = assertThrows(InvalidProdutionFilmException.class, () -> this.service.create(FilmRequestDTO, "diego@gmail.com"));
        assertEquals("Content 'prodution' is null", exception.getMessage());
    }

    @Test
    void testExceptionCreateFilmWithUrlEmpty() {
        var FilmRequestDTO = this.mocksFilms.mockFilmRequestDTOUrlEmpty();
        var exception = assertThrows(InvalidUrlImageFilmException.class, () -> this.service.create(FilmRequestDTO, "diego@gmail.com"));
        assertEquals("Content 'url' is empty", exception.getMessage());
    }

    @Test
    void testExceptionCreateFilmWithDUrlNull() {
        var FilmRequestDTO = this.mocksFilms.mockFilmRequestDTOUrlNull();
        var exception = assertThrows(InvalidUrlImageFilmException.class, () -> this.service.create(FilmRequestDTO, "diego@gmail.com"));
        assertEquals("Content 'url' is null", exception.getMessage());
    }

    @Test
    void testExceptionCreateFilmWithRuntimeNull() {
        var FilmRequestDTO = this.mocksFilms.mockFilmRequestDTORuntimeNull();
        var exception = assertThrows(InvalidRuntimeFilmException.class, () -> this.service.create(FilmRequestDTO, "diego@gmail.com"));
        assertEquals("Content 'runtime' is null", exception.getMessage());
    }

    @Test
    void testExceptionCreateFilmWithReleaseDateNull() {
        var FilmRequestDTO = this.mocksFilms.mockFilmRequestDTOReleaseDateNull();
        var exception = assertThrows(InvalidReleaseDateFilmException.class, () -> this.service.create(FilmRequestDTO, "diego@gmail.com"));
        assertEquals("Content 'release_date' is null", exception.getMessage());

    }


    }
