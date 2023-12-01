package me.dri.Catvie.unittest.filmservicestest;

import me.dri.Catvie.domain.adapters.services.director.DirectorServiceImpl;
import me.dri.Catvie.domain.adapters.services.film.FilmServiceImpl;
import me.dri.Catvie.domain.adapters.services.genre.GenreServiceImpl;
import me.dri.Catvie.domain.exceptions.NotFoundFilm;
import me.dri.Catvie.domain.exceptions.film.*;
import me.dri.Catvie.domain.models.dto.director.DirectorDTO;
import me.dri.Catvie.domain.models.dto.director.DirectorResponseDTO;
import me.dri.Catvie.domain.models.dto.film.FilmDTO;
import me.dri.Catvie.domain.models.dto.film.FilmResponseDTO;
import me.dri.Catvie.domain.models.dto.genre.GenreDTO;
import me.dri.Catvie.domain.models.entities.Director;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.models.entities.Genre;
import me.dri.Catvie.domain.ports.interfaces.director.DirectorServicePort;
import me.dri.Catvie.domain.ports.interfaces.film.FilmServicePort;
import me.dri.Catvie.domain.ports.interfaces.mappers.MapperFilmDomainPort;
import me.dri.Catvie.domain.ports.interfaces.genre.GenreServicesPort;
import me.dri.Catvie.domain.ports.repositories.DirectorRepositoryPort;
import me.dri.Catvie.domain.ports.repositories.FilmRepositoryPort;
import me.dri.Catvie.domain.ports.repositories.GenreRepositoryPort;
import me.dri.Catvie.unittest.mocks.MockDirector;
import me.dri.Catvie.unittest.mocks.MockFilm;
import me.dri.Catvie.unittest.mocks.MockGenre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class FilmServicesDomainUnitTest {

    FilmServicePort service;
    @Mock
    FilmRepositoryPort repository;
    @Mock
    MapperFilmDomainPort mapperPort;
    @Mock
    GenreRepositoryPort genreRepositoryPort;
    GenreServicesPort genreServicesPort;

    @Mock
    DirectorRepositoryPort directorRepositoryPort;

    DirectorServicePort directorServicePort;

    MockFilm mockEntitys;
    MockGenre mockGenre;

    MockDirector mockDirector;




    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        genreServicesPort = new GenreServiceImpl(genreRepositoryPort);
        directorServicePort = new DirectorServiceImpl(directorRepositoryPort);
        service = new FilmServiceImpl(repository, mapperPort, genreServicesPort, directorServicePort);
        mockEntitys = new MockFilm();
        mockGenre = new MockGenre();
        mockDirector = new MockDirector(mockEntitys);

    }


    @Test
    void testCreateFilm() {
        FilmDTO filmCreate = this.mockEntitys.mockFilmDto();
        Genre mockGenre = this.mockGenre.mockGenre();
        Set<GenreDTO> genreDTOS = this.mockGenre.mockGenres();
        Director mockDirector = this.mockDirector.mockDirector();
        DirectorDTO mockDirectoDTO = this.mockDirector.mockDirectorDTO();
        Film filmMock = this.mockEntitys.mockFilm();;
        when(this.genreRepositoryPort.findByName(any())).thenReturn(mockGenre);
        when(this.directorRepositoryPort.findByName(any())).thenReturn(mockDirector);
        var director = this.directorServicePort.findByName("Diego");
        assertNotNull(director);
        when(this.mapperPort.convertFilmDtoToFilm(filmCreate, genreDTOS, mockDirectoDTO)).thenReturn(filmMock);
        FilmResponseDTO result = this.service.create(filmCreate);
        verify(this.mapperPort, times(1)).convertFilmDtoToFilm(any(), any(), any());
        verify(this.repository, times(1)).create(any());
    }


    @Test
    void testFindFilmByTitle() {
        Film film = mock(Film.class);
        FilmDTO filmDto = mock(FilmDTO.class);
        when(this.repository.findByTitle("film test 1")).thenReturn(film);
        when(this.mapperPort.convertFilmToDto(any())).thenReturn(filmDto);
        var result = this.service.findByTitle("film test 1");
        assertTrue(result instanceof FilmDTO);
        verify(this.mapperPort, times(1)).convertFilmToDto(any());
    }

    @Test
    void testFindFilmById() {
        Film film = mock(Film.class);
        FilmDTO filmDTO = mock(FilmDTO.class);
        when(this.repository.findById(1L)).thenReturn(film);
        when(this.mapperPort.convertFilmToDto(any())).thenReturn(filmDTO);
        var result = this.service.findById(1L);
        System.out.println(result);
        assertEquals(result, filmDTO);
        verify(this.repository, times(1)).findById(any());
    }

    @Test
    void testFindAll() {
        List<Film> films = Collections.singletonList(mock(Film.class));
        List<FilmDTO> filmDtos = Collections.singletonList(mock(FilmDTO.class));
        when(this.repository.findAllFilmEntity()).thenReturn(films);
        when(this.mapperPort.convertListFilmToListDto(any())).thenReturn(filmDtos);
        var result = this.service.findAll();
        // Checking if result this a ListDto
        assertTrue(result instanceof List<FilmDTO>);
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
        var filmDto = this.mockEntitys.mockFilmDtoTitleEmpty();
        var exception = assertThrows(InvalidTitleFilmException.class, () -> this.service.create(filmDto));
        assertEquals("Content 'title' is empty", exception.getMessage());
    }
    @Test
    void testExceptionCreateFilmWithTitleNull() {
        var filmDto = this.mockEntitys.mockFilmDtoTitleNull();
        var exception = assertThrows(InvalidTitleFilmException.class, () -> this.service.create(filmDto));
        assertEquals("Content 'title' is null", exception.getMessage());
    }

    @Test
    void testExceptionCreateFilmWithLanguageEmpty() {
        var filmDto = this.mockEntitys.mockFilmDtoLanguageEmpty();
        var exception = assertThrows(InvalidLanguageFilmException.class, () -> this.service.create(filmDto));
        assertEquals("Content 'original_language' is empty", exception.getMessage());
    }
    @Test
    void testExceptionCreateFilmWithLanguageNull() {
        var filmDto = this.mockEntitys.mockFilmDtoLanguageNull();
        var exception = assertThrows(InvalidLanguageFilmException.class, () -> this.service.create(filmDto));
        assertEquals("Content 'original_language' is null", exception.getMessage());
    }

    @Test
    void testExceptionCreateFilmWithWriterEmpty() {
        var filmDto = this.mockEntitys.mockFilmDtoWriterEmpty();
        var exception = assertThrows(InvalidWriterFilmException.class, () -> this.service.create(filmDto));
        assertEquals("Content 'writer' is empty", exception.getMessage());
    }
    @Test
    void testExceptionCreateFilmWithWriterNull() {
        var filmDto = this.mockEntitys.mockFilmDtoWriterNull();
        var exception = assertThrows(InvalidWriterFilmException.class, () -> this.service.create(filmDto));
        assertEquals("Content 'writer' is null", exception.getMessage());
    }

    @Test
    void testExceptionCreateFilmWithDistributorEmpty() {
        var filmDto = this.mockEntitys.mockFilmDtoDistributorEmpty();
        var exception = assertThrows(InvalidDistributorFilmException.class, () -> this.service.create(filmDto));
        assertEquals("Content 'distributor' is empty", exception.getMessage());
    }
    @Test
    void testExceptionCreateFilmWithDistributorNull() {
        var filmDto = this.mockEntitys.mockFilmDtoDistributorNull();
        var exception = assertThrows(InvalidDistributorFilmException.class, () -> this.service.create(filmDto));
        assertEquals("Content 'distributor' is null", exception.getMessage());
    }

    @Test
    void testExceptionCreateFilmWithProductionEmpty() {
        var filmDto = this.mockEntitys.mockFilmDtoProductionEmpty();
        var exception = assertThrows(InvalidProdutionFilmException.class, () -> this.service.create(filmDto));
        assertEquals("Content 'prodution' is empty", exception.getMessage());
    }
    @Test
    void testExceptionCreateFilmWithProductionNull() {
        var filmDto = this.mockEntitys.mockFilmDtoProductionNully();
        var exception = assertThrows(InvalidProdutionFilmException.class, () -> this.service.create(filmDto));
        assertEquals("Content 'prodution' is null", exception.getMessage());
    }

    @Test
    void testExceptionCreateFilmWithUrlEmpty() {
        var filmDto = this.mockEntitys.mockFilmDtoUrlEmpty();
        var exception = assertThrows(InvalidUrlImageFilmException.class, () -> this.service.create(filmDto));
        assertEquals("Content 'url' is empty", exception.getMessage());
    }

    @Test
    void testExceptionCreateFilmWithDUrlNull() {
        var filmDto = this.mockEntitys.mockFilmDtoUrlNull();
        var exception = assertThrows(InvalidUrlImageFilmException.class, () -> this.service.create(filmDto));
        assertEquals("Content 'url' is null", exception.getMessage());
    }

    @Test
    void testExceptionCreateFilmWithRuntimeNull() {
        var filmDto = this.mockEntitys.mockFilmDtoRuntimeNull();
        var exception = assertThrows(InvalidRuntimeFilmException.class, () -> this.service.create(filmDto));
        assertEquals("Content 'runtime' is null", exception.getMessage());
    }

    @Test
    void testExceptionCreateFilmWithReleaseDateNull() {
        var filmDto = this.mockEntitys.mockFilmDtoReleaseDateNull();
        var exception = assertThrows(InvalidReleaseDateFilmException.class, () -> this.service.create(filmDto));
        assertEquals("Content 'release_date' is null", exception.getMessage());

    }

    @Test
    void testExceptionCreateFilmWithAverageCritic() {
        var filmDto = this.mockEntitys.mockFilmDtoAverageCriticNull();
        var exception = assertThrows(InvalidAverageCriticFilmException.class, () -> this.service.create(filmDto));
        assertEquals("Content 'average_rating_critic' is null", exception.getMessage());
    }


    @Test
    void testExceptionCreateFilmWithAverageAudienceNull() {
        var filmDto = this.mockEntitys.mockFilmDtoAverageAudienceNull();
        var exception = assertThrows(InvalidAverageAudienceFilmException.class, () -> this.service.create(filmDto));
        assertEquals("Content 'average_rating_audience' is null", exception.getMessage());
    }

    }
