package me.dri.Catvie.integrationtest.notestest;

import me.dri.Catvie.domain.enums.UserRole;
import me.dri.Catvie.integrationtest.config.ConfigAuthHeaders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
public class NotesAudiencesIntegrationTest {

    Map<String, String> header = new HashMap<>();

    private final String API_NOTES = "http://localhost:8080/api/notes/v1/";

    private final ConfigAuthHeaders configAuthHeaders = new ConfigAuthHeaders("mel@gmail.com", UserRole.USER); // Config Register to get token header authorization

    @BeforeEach
    void setup() {
        this.configAuthHeaders.authentication(this.header);
    }

    @Test
    void testAddNotesByFilmId() {
        given()
                .headers(this.header)
                .when().post(this.API_NOTES + "addNotesByFilmId/3.5/2/1")
                .then().assertThat().statusCode(201);
    }

}
