package me.dri.Catvie.integrationtest.filmservices;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import me.dri.Catvie.domain.consts.EndpointsConstants;
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
import org.springframework.http.MediaType;

import javax.print.attribute.standard.Media;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class FilmServicesIntegrationTest {
    Map<String, String> header = new HashMap<>();
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
        RestAssured.baseURI = EndpointsConstants.LOCALHOST + EndpointsConstants.ENDPOINT_AUTH;
    }


    @Test
    void testCreate() {
        FilmRequestDTO mockFilm = this.mockerFilm.mockFilmRequestDTO();
        mockFilm.setDirector(new DirectorRequestDTO("Diretor 1"));
        mockFilm.setTitle("TituloNemValeDeNada");
        mockFilm.setPosterUrl("http:posterurl/teste");
        given().headers(this.header).when().contentType(ContentType.JSON).body(mockFilm).when().post("/create")
        .then().statusCode(201).body("title", equalTo(mockFilm.getTitle()));
    }
    @Test
    void testFindAll() {
        given()
                .headers(this.header)
                .when()
                .headers("Accept", "application/json")
                .get("/all")
                .then().assertThat()
                .statusCode(200);
    }

    @Test
    void testFindByTitle() {
        given()
                .headers(this.header)
                .when()
                .contentType(ContentType.JSON)
                .get("/byTitle/O Convento")
                .then()
                .body("productionCo", equalTo("Metro-Goldwyn-Mayer"))
                .statusCode(200);
    }

    @Test
    void testNotFoundFilmByTitle() {
        given()
                .headers(this.header)
                .when()
                .contentType(ContentType.JSON)
                .get("/byTitle/EsseNomeAquiNuncaVaiExistir")
                .then()
                .statusCode(404);
    }


    @Test
    void testFindById()  {
        given()
                .headers(this.header)
                .when()
                .contentType(ContentType.JSON)
                .get("/byId/5")
                .then()
                .body("title", equalTo("O Convento"))
                .statusCode(200);
    }

    @Test
    void testNotFoundFilmById() {
        given()
                .headers(this.header)
                .when()
                .contentType(ContentType.JSON)
                .get("/byId/" + Long.MAX_VALUE) // This value probably don't exist
                .then()
                .statusCode(404);
    }

    @Test
    void testDeleteById() {
        given()
                .headers(this.header)
                .when()
                .contentType(ContentType.JSON)
                .delete("/deleteById/1")
                .then()
                .statusCode(204);
    }

    @Test
    void testNotFoundFilmDeleteById() {
        given()
                .headers(this.header)
                .when()
                .contentType(ContentType.JSON)
                .delete("/deleteById/" + Long.MAX_VALUE)
                .then()
                .statusCode(404);
    }

}
