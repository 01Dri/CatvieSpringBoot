package me.dri.Catvie.integrationtest.filmservices;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import me.dri.Catvie.controllers.FilmController;
import me.dri.Catvie.domain.adapters.services.mappers.MapperEntitiesImpl;
import me.dri.Catvie.domain.enums.Genres;
import me.dri.Catvie.domain.models.dto.director.DirectorCreateDTO;
import me.dri.Catvie.domain.models.dto.film.FilmDTO;
import me.dri.Catvie.domain.models.dto.genre.GenreDTO;
import me.dri.Catvie.domain.ports.interfaces.auth.TokenServicesPort;
import me.dri.Catvie.domain.ports.interfaces.film.MapperEntities;
import me.dri.Catvie.domain.ports.repositories.FilmRepositoryPort;
import me.dri.Catvie.infra.adapters.repositories.FilmAdapter;
import me.dri.Catvie.infra.ports.DirectorRepositoryJPA;
import me.dri.Catvie.infra.ports.FilmRepositoryJPA;
import me.dri.Catvie.infra.ports.GenreRepositoryJPA;
import me.dri.Catvie.infra.tokens.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;
import java.util.Set;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class FilmServiceTest {

    @Autowired
    MockMvc mockMvc;

    FilmRepositoryJPA repositoryJPA;
    FilmRepositoryPort filmRepositoryPort;
    GenreRepositoryJPA genreRepositoryPort;
    DirectorRepositoryJPA directorRepositoryPort;
    MapperEntities mapperEntities;

    TokenServicesPort tokenServicesPort;

    String token;


    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(FilmController.class)
                .build();

        mapperEntities = new MapperEntitiesImpl();
        filmRepositoryPort = new FilmAdapter(repositoryJPA, mapperEntities, genreRepositoryPort, directorRepositoryPort);
        tokenServicesPort = new TokenService();
    }



}
