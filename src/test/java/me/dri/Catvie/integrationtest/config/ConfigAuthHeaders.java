package me.dri.Catvie.integrationtest.config;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import me.dri.Catvie.domain.consts.EndpointsConstants;
import me.dri.Catvie.domain.consts.HttpConstants;
import me.dri.Catvie.domain.enums.UserRole;
import me.dri.Catvie.domain.models.dto.auth.LoginDTO;
import me.dri.Catvie.domain.models.dto.auth.RegisterDTO;
import org.junit.jupiter.api.BeforeEach;

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


    @BeforeEach
    void setup() {
        RestAssured.baseURI = EndpointsConstants.LOCALHOST + EndpointsConstants.ENDPOINT_AUTH;
    }
    public void authentication(Map<String, String> header) {
        this.token = registerUserForTests(this.email);
        header.put("Accept", HttpConstants.CONTENT_TYPE_JSON);
        header.put("Content-Type", HttpConstants.CONTENT_TYPE_JSON);
        header.put("Authorization", "Bearer " + token);
    }

    private String registerUserForTests(String email) {
        RegisterDTO registerDTO = new RegisterDTO("Diego", "Henrique", email, "teste12345678", this.role);
        this.token = this.getToken(registerDTO);
        if (this.token == null) {
            given()
                    .body(registerDTO)
                    .contentType(ContentType.JSON)
                    .when()
                    .post( EndpointsConstants.LOCALHOST +  EndpointsConstants.ENDPOINT_AUTH + "/register")
                    .then()
                    .assertThat()
                    .statusCode(201);
            return this.getToken(registerDTO);
        } else {
            return this.token;
        }

    }

    private String getToken(RegisterDTO registerDTO) {
        // Generate token
        LoginDTO loginDTO = new LoginDTO(registerDTO.email(), registerDTO.password());
        this.token = given()
                .contentType(ContentType.JSON)
                .body(loginDTO)
                .when()
                .post(EndpointsConstants.LOCALHOST +  EndpointsConstants.ENDPOINT_AUTH + "/login")
                .then()
                .extract()
                .jsonPath()
                .get("token");
        return this.token;
    }
}
