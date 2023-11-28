package me.dri.Catvie.integrationtest.filmservices;

import io.restassured.http.ContentType;
import me.dri.Catvie.domain.enums.UserRole;
import me.dri.Catvie.domain.models.dto.auth.LoginDTO;
import me.dri.Catvie.domain.models.dto.auth.RegisterDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class FilmServicesIntegrationTest {
    Map<String, String> header = new HashMap<>();

    private final String API_FILM = "http://localhost:8080/api/film/v1/";

    @BeforeEach
    void setup() {
        authentication();
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
                .body("production_co", equalTo("Metro-Goldwyn-Mayer"))
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
                .when().get(this.API_FILM + "findById/2")
                .then()
                .body("title", equalTo("Uma Fada Veio Me Visitar"))
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

    // Test the create and update to finish






    public void authentication() {
        var token = registerUserForTests("digas@gamil.com");
        this.header.put("Accept", "application/json");
        this.header.put("Authorization", "Bearer " + token);
    }


    public String registerUserForTests(String email) {
        RegisterDTO registerDTO = new RegisterDTO("Diego", "Henrique", email, "teste12345678", UserRole.USER);
        var token = this.getToken(registerDTO);
        if (token == null) {
            given()
                    .contentType(ContentType.JSON)
                    .body(registerDTO).when()
                    .post("http://localhost:8080/api/auth/v1/register")
                    .then()
                    .assertThat()
                    .statusCode(201);
            return this.getToken(registerDTO);
        } else {
            return token;
        }

    }

    public String getToken(RegisterDTO registerDTO) {
        // Generate token
        String token = "";
        LoginDTO loginDTO = new LoginDTO(registerDTO.email(), registerDTO.password());
        token = given()
                .contentType(ContentType.JSON)
                .body(loginDTO)
                .when()
                .post("http://localhost:8080/api/auth/v1/login")
                .jsonPath()
                .get("token");
        return token;
    }


}
