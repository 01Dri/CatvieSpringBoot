package me.dri.Catvie.unittest.filmservicestest;

import me.dri.Catvie.domain.ports.interfaces.MapperEntities;
import me.dri.Catvie.domain.ports.repositories.FilmRepositoryPort;
import me.dri.Catvie.infra.adapters.repositories.FilmAdapter;
import me.dri.Catvie.infra.ports.FilmRepositoryJPA;
import me.dri.Catvie.infra.ports.MapperUserPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class FilmServicesInfra {

    @Mock
    FilmRepositoryJPA filmRepositoryJPA;
    @Mock
    MapperEntities mapperEntities;

    FilmRepositoryPort service;

    @BeforeEach
    void setup() {
        service = new FilmAdapter(filmRepositoryJPA, mapperEntities);
    }

    @Test
    void AlreadyExistsFilm() {

    }
}
