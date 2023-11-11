package me.dri.Catvie.integrationtest.filmservices;

import io.restassured.RestAssured;
import me.dri.Catvie.domain.adapters.services.mappers.MapperEntitiesImpl;
import me.dri.Catvie.domain.ports.interfaces.FilmServicePort;
import me.dri.Catvie.domain.ports.interfaces.MapperEntities;
import me.dri.Catvie.domain.ports.repositories.FilmRepositoryPort;
import me.dri.Catvie.infra.adapters.repositories.FilmAdapter;
import me.dri.Catvie.infra.ports.FilmRepositoryJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class FilmServiceTest {

    FilmRepositoryJPA repositoryJPA;
    FilmRepositoryPort filmRepositoryPort;
    MapperEntities mapperEntities;

    FilmServicePort filmServicePort;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost:8080/";
        RestAssured.port = 8080;
        mapperEntities = new MapperEntitiesImpl();
        filmRepositoryPort = new FilmAdapter(repositoryJPA, mapperEntities);
    }

    @Test
    void testFindById() {
        get("/film/v1/findById/1").then().statusCode(200)
                .body("title", equalTo("Os Mercenários 4"));
    }

    @Test
    void testFindAll() {
        get("/film/v1/findAll").then().statusCode(200).body("$", hasSize(greaterThan(0)));
    }
}
