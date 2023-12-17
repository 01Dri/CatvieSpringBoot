package me.dri.Catvie.integrationtest.authtests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import me.dri.Catvie.domain.consts.EndpointsConstants;
import me.dri.Catvie.domain.enums.UserRole;
import me.dri.Catvie.domain.models.dto.auth.LoginDTO;
import me.dri.Catvie.domain.models.dto.auth.RegisterDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class AuthServicesIntegrationTests {




    @BeforeEach
    public void setup() {
        RestAssured.baseURI = EndpointsConstants.LOCALHOST + EndpointsConstants.ENDPOINT_AUTH;

    }

    @Test
    void registerTestSucessfull() {
        RegisterDTO obj = new RegisterDTO("Diego", "Henrique", "heenrikk4@gmail.com", "12345678", UserRole.USER);
        given().when().contentType(ContentType.JSON).body(obj).when().post("/register")
                .then().statusCode(201).body("email", equalTo(obj.email()));
    }


    @Test
    void loginTestSuccessfull() {
        LoginDTO loginDTO = new LoginDTO("heenrikk4@gmail.com", "12345678");
        given().when().contentType(ContentType.JSON).body(loginDTO).post("/login")
                .then().statusCode(201);
    }

    @Test
    void registerUserFailedBecauseFirstNameIsIncorrect() {
        RegisterDTO obj = new RegisterDTO("", "Henrique", "teste123@gmail.com", "12345678", UserRole.USER);
        given().when().contentType(ContentType.JSON).body(obj).when().post("/register")
                .then().statusCode(400).body("message", equalTo("Error invalid input DTO"));;
    }


    @Test
    void registerUserFailedBecauseLastNameIsIncorrect() {
        RegisterDTO obj = new RegisterDTO("Diego", "", "teste1234@gmail.com", "12345678", UserRole.USER);
        given().when().contentType(ContentType.JSON).body(obj).when().post("/register")
                .then().statusCode(400).body("message", equalTo("Error invalid input DTO"));
    }

    @Test
    void registerUserFailedBecauseEmailIsIncorrect() {
        RegisterDTO obj = new RegisterDTO("Diego", "Henrique", "", "12345678", UserRole.USER);
        given().when().contentType(ContentType.JSON).body(obj).when().post("/register")
                .then().statusCode(400).body("message", equalTo("Error invalid input DTO"));;
    }

    @Test
    void registerUserFailedBecausePasswordIsIncorrect() {
        RegisterDTO obj = new RegisterDTO("Diego", "Henrique", "teste12345@gmail.com", "", UserRole.USER);
        given().when().contentType(ContentType.JSON).body(obj).when().post("/register")
                .then().statusCode(400).body("message", equalTo("Error invalid input DTO"));;
    }


}
