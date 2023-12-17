package me.dri.Catvie.integrationtest.userservices;

import io.restassured.RestAssured;
import me.dri.Catvie.domain.consts.EndpointsConstants;
import me.dri.Catvie.domain.enums.UserRole;
import me.dri.Catvie.integrationtest.config.ConfigAuthHeaders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserServicesIntegrationTest {

    Map<String, String> header = new HashMap<>();
    private final String API_USER = "http://localhost:8080/v1/api/users";

    private final ConfigAuthHeaders configAuthHeaders = new ConfigAuthHeaders("diegotestes123@gmail.com", UserRole.ADMIN); // Config Register to get token header authorization

    @BeforeEach
    void setup() {
        RestAssured.baseURI = EndpointsConstants.LOCALHOST + EndpointsConstants.ENDPOINT_NOTES;
        configAuthHeaders.authentication(this.header);
    }

    @Test
    void testFindById() {
        given().headers(this.header).when().get("/byId/1").then().statusCode(200).body("firstName", equalTo("John"));
    }

    @Test
    void testNotFoundUserById() {
        given().headers(this.header).when().get("/byId/1123123123").then().statusCode(204);
    }

}
