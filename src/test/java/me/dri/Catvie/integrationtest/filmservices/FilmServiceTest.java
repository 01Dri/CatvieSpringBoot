package me.dri.Catvie.integrationtest.filmservices;

import io.restassured.http.ContentType;
import me.dri.Catvie.domain.enums.UserRole;
import me.dri.Catvie.domain.models.dto.auth.LoginDTO;
import me.dri.Catvie.domain.models.dto.auth.RegisterDTO;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class FilmServiceTest {
    Map<String, String> header = new HashMap<>();

    @Test
    void testFindAll() {
        var objRegister = registerUserForTests("diego@gmail.com");
        var token = getToken(objRegister);
        this.header.put("Accept", "application/json");
        this.header.put("Authorization", "Bearer " + token);
        given()
                .headers(this.header)
                .when().get("http://localhost:8080/api/film/v1/findAll")
                .then().assertThat()
                .statusCode(200);
    }

    @Test
    void testFindByTitle() {
        var objRegister = registerUserForTests("mel@gmail.com");
        var token = getToken(objRegister);
        this.header.put("Accept", "application/json");
        this.header.put("Authorization", "Bearer " + token);
        given()
                .headers(this.header)
                .when().get("http://localhost:8080/api/film/v1/findByTitle/O Convento")
                .then()
                .body("production_co", equalTo("Metro-Goldwyn-Mayer"))
                .statusCode(200);
    }


    public RegisterDTO registerUserForTests(String email) {
        RegisterDTO registerDTO = new RegisterDTO("Diego", "Henrique", email, "teste12345678", UserRole.USER);
        given()
                .contentType(ContentType.JSON)
                .body(registerDTO).when()
                .post("http://localhost:8080/api/auth/v1/register")
                .then()
                .assertThat()
                .statusCode(201);
        return registerDTO;
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
