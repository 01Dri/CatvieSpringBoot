package me.dri.Catvie.integrationtest.userservices;

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
    private final ConfigAuthHeaders configAuthHeaders = new ConfigAuthHeaders("diegotestes123@gmail.com", UserRole.ADMIN); // Config Register to get token header authorization


    @BeforeEach
    void setup() {
        configAuthHeaders.authentication(this.header);
    }

    @Test
    void testFindById() {
        String API_USER = "http://localhost:8080/api/user/v1/";
        given().headers(this.header).when().get(API_USER + "findById/1").then().statusCode(200).body("firstName", equalTo("John"));
    }

    @Test
    void testNotFoundUserById() {
        String API_USER = "http://localhost:8080/api/user/v1/";
        given().headers(this.header).when().get(API_USER + "findById/1123123123").then().statusCode(204);
    }

}
