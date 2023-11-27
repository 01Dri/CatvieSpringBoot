package me.dri.Catvie.integrationtest.filmservices;

import io.restassured.http.ContentType;
import me.dri.Catvie.domain.models.dto.auth.LoginDTO;
import me.dri.Catvie.domain.models.dto.auth.RegisterDTO;
import me.dri.Catvie.unittest.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;


public class FilmServiceTest {

    MockUser mockUser;
    String token = "";

    @BeforeEach
    public void setup() {
        this.mockUser = new MockUser();
        // Register the user
        RegisterDTO registerDTO = this.mockUser.mockRegisterDTO();
        given()
                .contentType(ContentType.JSON)
                .body(registerDTO).when()
                .post("http://localhost:8080/api/auth/v1/register")
                .then()
                .assertThat()
                .statusCode(201);

        // Generate token
        LoginDTO loginDTO = this.mockUser.mockLoginDTO();
        token = given()
                .contentType(ContentType.JSON)
                .body(loginDTO)
                .when()
                .post("http://localhost:8080/api/auth/v1/login")
                .jsonPath()
                .get("token");
    }


    @Test
    void testFindAll() {
        Map<String, String> headers = new HashMap<>() {
            {
                put("Accept", "application/json");
                put("Authorization", "Bearer " + token);
            }
        };
        given()
                .headers(headers)
                .when().get("http://localhost:8080/api/film/v1/findAll")
                .then().assertThat()
                .statusCode(200);

    }


}
