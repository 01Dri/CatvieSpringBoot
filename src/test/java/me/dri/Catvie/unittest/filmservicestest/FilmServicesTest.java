package me.dri.Catvie.unittest.filmservicestest;

import me.dri.Catvie.domain.adapters.services.director.DirectorServiceImpl;
import me.dri.Catvie.domain.adapters.services.film.FilmServiceImpl;
import me.dri.Catvie.domain.adapters.services.genre.GenreServiceImpl;
import me.dri.Catvie.domain.exceptions.ContentInformationsFilmMissing;
import me.dri.Catvie.domain.exceptions.NotFoundFilm;
import me.dri.Catvie.domain.models.dto.director.DirectorResponseDTO;
import me.dri.Catvie.domain.models.dto.film.FilmDTO;
import me.dri.Catvie.domain.models.dto.genre.GenreDTO;
import me.dri.Catvie.domain.models.entities.Director;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.models.entities.Genre;
import me.dri.Catvie.domain.ports.interfaces.director.DirectorServicePort;
import me.dri.Catvie.domain.ports.interfaces.film.FilmServicePort;
import me.dri.Catvie.domain.ports.interfaces.film.MapperEntitiesPort;
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
public class FilmServicesTest  {

    FilmServicePort service;
    @Mock
    FilmRepositoryPort repository;
    @Mock
    MapperEntitiesPort mapperPort;
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

//    @Test
//    void testUpdateFilm() {
//        FilmDTO filmDto = mock(FilmDTO.class);
//        FilmEntity film = mock(FilmEntity.class);
//        when(this.repositoryJPA.findFilmById(any())).thenReturn(film);
//        this.service.update();
//
//    }

    @Test
    void testCreateFilm() {
        FilmDTO filmCreate = this.mockEntitys.mockFilmDto();
        Genre mockGenre = this.mockGenre.mockGenre();
        Set<GenreDTO> genreDTOS = this.mockGenre.mockGenres();
        Director mockDirector = this.mockDirector.mockDirector();
        DirectorResponseDTO mockDirectoDTO = this.mockDirector.mockDirectorDTO();
        Film filmMock = this.mockEntitys.mockFilm();;
        when(this.genreRepositoryPort.findByName(any())).thenReturn(mockGenre);
        when(this.directorRepositoryPort.findByName(any())).thenReturn(mockDirector);
        var director = this.directorServicePort.findByName("Diego");
        assertNotNull(director);
        when(this.mapperPort.convertFilmDtoToFilm(filmCreate, genreDTOS, mockDirectoDTO)).thenReturn(filmMock);
        this.service.create(filmCreate);
        verify(this.mapperPort, times(1)).convertFilmDtoToFilm(any(), any(), any());
    }

    @Test
    void testExceptionMissingContentInCreateFilm() {
        FilmDTO filmDtoInvalid = this.mockEntitys.mockFilmDtoWithNullValue();
        assertThrows(ContentInformationsFilmMissing.class, () -> this.service.create(filmDtoInvalid));
        verify(this.mapperPort, never()).convertFilmToDto(any());
        verify(this.repository, never()).save(any());

    }

    @Test
    void testSaveFilm() {
        FilmDTO filmDto = this.mockEntitys.mockFilmDto();
        Film filmToSave = this.mockEntitys.mockFilm();
        Genre mockGenre = this.mockGenre.mockGenre();
        Director mockDirector = this.mockDirector.mockDirector();
        DirectorResponseDTO mockDirectoDTO = this.mockDirector.mockDirectorDTO();
        Set<GenreDTO> genres = this.mockGenre.mockGenres();
        when(this.genreRepositoryPort.findByName(any())).thenReturn(mockGenre);
        when(this.directorRepositoryPort.findByName(any())).thenReturn(mockDirector);
        when(this.mapperPort.convertFilmDtoToFilm(filmDto, genres, mockDirectoDTO)).thenReturn(filmToSave);
        this.service.save(filmDto);
        verify(this.repository, times(1)).save(filmToSave);
    }

    @Test
    void testFindFilmByTitle() {
        Film film = mock(Film.class);
        FilmDTO filmDto = mock(FilmDTO.class);
        when(this.repository.findByTitle("film test 1")).thenReturn(film);
        when(this.mapperPort.convertFilmToDto(any())).thenReturn(filmDto);
        var result = this.service.findByTitle("film test 1");
        System.out.println(result);
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
        when(this.repository.findAll()).thenReturn(films);
        when(this.mapperPort.convertListFilmToListDto(any())).thenReturn(filmDtos);
        var result = this.service.findAll();

        // Checking if result this a ListDto
        assertTrue(result instanceof List<FilmDTO>);
        verify(this.mapperPort, times(1)).convertListFilmToListDto(any());
    }

    @Test
    void testMissingInformationOfFilmToSave() {
        FilmDTO mockFilmDtoWithNullValue = this.mockEntitys.mockFilmDtoWithNullValue();
        Film film = this.mockEntitys.mockFilm();
        Set<GenreDTO> genreDTO = this.mockGenre.mockGenres();
        DirectorResponseDTO mockDirectoDTO = this.mockDirector.mockDirectorDTO();
        when(this.mapperPort.convertFilmDtoToFilm(mockFilmDtoWithNullValue, genreDTO, mockDirectoDTO)).thenReturn(film);
        assertThrows(ContentInformationsFilmMissing.class, () -> this.service.save(mockFilmDtoWithNullValue));
    }

    @Test
    void testMissingInformationOfFilmToCreate() {
        FilmDTO mockFilmDtoWithNullValue = this.mockEntitys.mockFilmDtoWithNullValue();
        Film film = this.mockEntitys.mockFilm();
        Set<GenreDTO> genreDTO = this.mockGenre.mockGenres();
        DirectorResponseDTO mockDirectoDTO = this.mockDirector.mockDirectorDTO();
        when(this.mapperPort.convertFilmDtoToFilm(mockFilmDtoWithNullValue, genreDTO, mockDirectoDTO)).thenReturn(film);
        assertThrows(ContentInformationsFilmMissing.class, () -> this.service.create(mockFilmDtoWithNullValue));
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
    void testFailedToDeleteFilmById() {
        when(this.repository.findById(1L)).thenReturn(null);
        assertThrows(NotFoundFilm.class, () -> this.service.deleteById(1L));
    }

    @Test
    void testDeleteFilm() {
        Film filmToDelete = this.mockEntitys.mockFilm();
        when(this.repository.findById(1L)).thenReturn(filmToDelete);
        this.service.deleteById(1L);
        verify(this.repository, times(1)).delete(filmToDelete);
    }



}
