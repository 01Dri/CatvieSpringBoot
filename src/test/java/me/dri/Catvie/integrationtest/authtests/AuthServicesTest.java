package me.dri.Catvie.integrationtest.authtests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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

public class AuthServicesTest {




    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost:8080/api/auth/v1";

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
                .then().statusCode(400).body("message", equalTo("FirstName validation error"));;
    }


    @Test
    void registerUserFailedBecauseLastNameIsIncorrect() {
        RegisterDTO obj = new RegisterDTO("Diego", "", "teste1234@gmail.com", "12345678", UserRole.USER);
        given().when().contentType(ContentType.JSON).body(obj).when().post("/register")
                .then().statusCode(400).body("message", equalTo("LastName validation error"));
    }

    @Test
    void registerUserFailedBecauseEmailIsIncorrect() {
        RegisterDTO obj = new RegisterDTO("Diego", "Henrique", "", "12345678", UserRole.USER);
        given().when().contentType(ContentType.JSON).body(obj).when().post("/register")
                .then().statusCode(400).body("message", equalTo("Email validation error"));;
    }

    @Test
    void registerUserFailedBecausePasswordIsIncorrect() {
        RegisterDTO obj = new RegisterDTO("Diego", "Henrique", "teste12345@gmail.com", "", UserRole.USER);
        given().when().contentType(ContentType.JSON).body(obj).when().post("/register")
                .then().statusCode(400).body("message", equalTo("Password validation error"));;
    }


}
