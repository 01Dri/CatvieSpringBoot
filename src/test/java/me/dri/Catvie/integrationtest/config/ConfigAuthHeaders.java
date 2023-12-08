package me.dri.Catvie.integrationtest.config;

import io.restassured.http.ContentType;
import me.dri.Catvie.domain.enums.UserRole;
import me.dri.Catvie.domain.models.dto.auth.LoginDTO;
import me.dri.Catvie.domain.models.dto.auth.RegisterDTO;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ConfigAuthHeaders {


    private final String email;

    private String token;

    private final UserRole role;

    public ConfigAuthHeaders(String email, UserRole role) {
        this.email = email;
        this.role = role;
    }





    public void authentication(Map<String, String> header) {
        this.token = registerUserForTests(this.email);
        header.put("Accept", "application/json");
        header.put("Authorization", "Bearer " + token);
    }


    public String registerUserForTests(String email) {
        RegisterDTO registerDTO = new RegisterDTO("Diego", "Henrique", email, "teste12345678", this.role);
        this.token = this.getToken(registerDTO);
        if (this.token == null) {
            given()
                    .contentType(ContentType.JSON)
                    .body(registerDTO).when()
                    .post("http://localhost:8080/api/auth/v1/register")
                    .then()
                    .assertThat()
                    .statusCode(201);
            return this.getToken(registerDTO);
        } else {
            return this.token;
        }

    }

    public String getToken(RegisterDTO registerDTO) {
        // Generate token
        LoginDTO loginDTO = new LoginDTO(registerDTO.email(), registerDTO.password());
        this.token = given()
                .contentType(ContentType.JSON)
                .body(loginDTO)
                .when()
                .post("http://localhost:8080/api/auth/v1/login")
                .jsonPath()
                .get("token");
        return this.token;
    }
}
