package me.dri.Catvie.integrationtest.filmservices;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import me.dri.Catvie.domain.adapters.services.mappers.MapperEntitiesImpl;
import me.dri.Catvie.domain.ports.interfaces.film.MapperEntities;
import me.dri.Catvie.domain.ports.repositories.FilmRepositoryPort;
import me.dri.Catvie.infra.adapters.repositories.FilmAdapter;
import me.dri.Catvie.infra.ports.DirectorRepositoryJPA;
import me.dri.Catvie.infra.ports.FilmRepositoryJPA;
import me.dri.Catvie.infra.ports.GenreRepositoryJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.*;

public class FilmServiceTest {

    FilmRepositoryJPA repositoryJPA;
    FilmRepositoryPort filmRepositoryPort;
    GenreRepositoryJPA genreRepositoryPort;
    DirectorRepositoryJPA directorRepositoryPort;
    MapperEntities mapperEntities;


    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost:8080/";
        RestAssured.port = 8080;
        mapperEntities = new MapperEntitiesImpl();
        filmRepositoryPort = new FilmAdapter(repositoryJPA, mapperEntities, genreRepositoryPort, directorRepositoryPort);
    }

    @Test
    void testFindById() {
        get("/film/v1/findById/1")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("title", equalTo("Os Mercenários 4"));;
    }

    @Test
    void testFindAll() {
        get("/film/v1/findAll")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("$", hasSize(greaterThan(0)));
    }

    @Test
    void testFindByTitle() {
        get("/film/v1/findByTitle/AViagemdoTempo")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("writer", equalTo("Luisa May Alcott"));
    }


}
