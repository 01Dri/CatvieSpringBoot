package me.dri.Catvie.unittest.filmservicestest;

import me.dri.Catvie.domain.models.dto.film.FilmDTO;
import me.dri.Catvie.domain.models.entities.Film;
import me.dri.Catvie.domain.ports.interfaces.film.MapperEntities;
import me.dri.Catvie.domain.ports.repositories.FilmRepositoryPort;
import me.dri.Catvie.infra.adapters.repositories.FilmAdapter;
import me.dri.Catvie.infra.ports.DirectorRepositoryJPA;
import me.dri.Catvie.infra.ports.FilmRepositoryJPA;
import me.dri.Catvie.infra.ports.GenreRepositoryJPA;
import me.dri.Catvie.unittest.mocks.MockFilm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class FilmServicesInfra {

    @Mock
    FilmRepositoryJPA filmRepositoryJPA;

    @Mock
    GenreRepositoryJPA genreRepositoryJPA;
    @Mock
    DirectorRepositoryJPA directorRepositoryJPA;
    @Mock
    MapperEntities mapperEntities;

    FilmRepositoryPort service;

    MockFilm mockFilm;

    @BeforeEach
    void setup() {
        mockFilm = new MockFilm();
        service = new FilmAdapter(filmRepositoryJPA, mapperEntities, genreRepositoryJPA, directorRepositoryJPA);
    }

    @Test
    void AlreadyExistsFilm() {
        var filmeEntityMock = this.mockFilm.mockFilmEntity();
        when(this.filmRepositoryJPA.findFilmByTitle(filmeEntityMock.getTitle())).thenReturn(Optional.of(filmeEntityMock));
    }
}
