package me.dri.Catvie.integrationtest.filmservices;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import me.dri.Catvie.domain.adapters.services.mappers.MapperEntitiesImpl;
import me.dri.Catvie.domain.enums.Genres;
import me.dri.Catvie.domain.models.dto.director.DirectorCreateDTO;
import me.dri.Catvie.domain.models.dto.film.FilmDTO;
import me.dri.Catvie.domain.models.dto.genre.GenreDTO;
import me.dri.Catvie.domain.ports.interfaces.film.MapperEntities;
import me.dri.Catvie.domain.ports.repositories.FilmRepositoryPort;
import me.dri.Catvie.infra.adapters.repositories.FilmAdapter;
import me.dri.Catvie.infra.ports.DirectorRepositoryJPA;
import me.dri.Catvie.infra.ports.FilmRepositoryJPA;
import me.dri.Catvie.infra.ports.GenreRepositoryJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Set;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class FilmServiceTest {

    FilmRepositoryJPA repositoryJPA;
    FilmRepositoryPort filmRepositoryPort;
    GenreRepositoryJPA genreRepositoryPort;
    DirectorRepositoryJPA directorRepositoryPort;
    MapperEntities mapperEntities;


    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost:8080/api/film/v1";
        mapperEntities = new MapperEntitiesImpl();
        filmRepositoryPort = new FilmAdapter(repositoryJPA, mapperEntities, genreRepositoryPort, directorRepositoryPort);
    }

    @Test
    void testFindById() {
        get("/findById/1")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("title", equalTo("Os Mercenários 4"));;
    }

    @Test
    void testFindAll() {
        get("/findAll")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("$", hasSize(greaterThan(0)));
    }

    @Test
    void testFindByTitle() {
        get("/findByTitle/AViagemdoTempo")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("writer", equalTo("Luisa May Alcott"));
    }

    @Test
    void testCreateFilm() {
        GenreDTO genre = new GenreDTO(Genres.ACTION);
        FilmDTO film = new FilmDTO("", Set.of(genre), "english",
                new DirectorCreateDTO("diego"), "Diego",
                new Date(),150, "disney",
                "diego", 6.0,
                8.5, "teste");
        given().when().contentType(ContentType.JSON).body(film).post("/create")
                .then().statusCode(201).body("writer", equalTo("Nome do Escrito"));
    }



}
