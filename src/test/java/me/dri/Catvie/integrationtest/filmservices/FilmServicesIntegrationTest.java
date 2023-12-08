package me.dri.Catvie.integrationtest.filmservices;

import io.restassured.http.ContentType;
import me.dri.Catvie.domain.enums.UserRole;
import me.dri.Catvie.domain.models.dto.director.DirectorRequestDTO;
import me.dri.Catvie.domain.models.dto.film.FilmRequestDTO;
import me.dri.Catvie.domain.models.dto.genre.GenreRequestDTO;
import me.dri.Catvie.integrationtest.config.ConfigAuthHeaders;
import me.dri.Catvie.unittest.mocks.MockDirector;
import me.dri.Catvie.unittest.mocks.MockFilm;
import me.dri.Catvie.unittest.mocks.MockGenre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class FilmServicesIntegrationTest {
    Map<String, String> header = new HashMap<>();
    private final String API_FILM = "http://localhost:8080/api/film/v1/";
    private final ConfigAuthHeaders configAuthHeaders = new ConfigAuthHeaders("melteste@gmail.com", UserRole.ADMIN); // Config Register to get token header authorization
    MockGenre mockerGenre;
    MockDirector mockerDirector;

    MockFilm mockerFilm;
    @BeforeEach
    void setup() {
        configAuthHeaders.authentication(this.header);
        this.mockerGenre = new MockGenre();
        this.mockerFilm = new MockFilm();
        this.mockerDirector = new MockDirector(mockerFilm);
    }


    @Test
    void testCreate() {
        FilmRequestDTO mockFilm = this.mockerFilm.mockFilmRequestDTO();
        mockFilm.setDirector(new DirectorRequestDTO("Diretor 1"));
        mockFilm.setTitle("TituloNemValeDeNada");
        mockFilm.setPosterUrl("http:posterurl/teste");
        given().headers(this.header).when().contentType(ContentType.JSON).body(mockFilm).when().post(this.API_FILM + "create")
        .then().statusCode(201).body("title", equalTo(mockFilm.getTitle()));
    }
    @Test
    void testFindAll() {
        given()
                .headers(this.header)
                .when().get(this.API_FILM + "findAll")
                .then().assertThat()
                .statusCode(200);
    }

    @Test
    void testFindByTitle() {
        given()
                .headers(this.header)
                .when().get(this.API_FILM + "findByTitle/O Convento")
                .then()
                .body("productionCo", equalTo("Metro-Goldwyn-Mayer"))
                .statusCode(200);
    }

    @Test
    void testNotFoundFilmByTitle() {
        given()
                .headers(this.header)
                .when().get(this.API_FILM + "findByTitle/EsseNomeAquiNuncaVaiExistir")
                .then()
                .statusCode(404);
    }


    @Test
    void testFindById()  {
        given()
                .headers(this.header)
                .when().get(this.API_FILM + "findById/5")
                .then()
                .body("title", equalTo("O Convento"))
                .statusCode(200);
    }

    @Test
    void testNotFoundFilmById() {
        given()
                .headers(this.header)
                .when().get(this.API_FILM + "findById/" + Long.MAX_VALUE) // This value probably don't exist
                .then()
                .statusCode(404);
    }

    @Test
    void testDeleteById() {
        given()
                .headers(this.header)
                .when().delete(this.API_FILM + "deleteById/1")
                .then()
                .statusCode(204);
    }

    @Test
    void testNotFoundFilmDeleteById() {
        given()
                .headers(this.header)
                .when().delete(this.API_FILM + "deleteById/" + Long.MAX_VALUE)
                .then()
                .statusCode(404);
    }

}
