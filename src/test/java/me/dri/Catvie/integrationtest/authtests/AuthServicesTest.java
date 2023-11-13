package me.dri.Catvie.integrationtest.authtests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import me.dri.Catvie.domain.adapters.services.AuthenticationServiceImpl;
import me.dri.Catvie.domain.enums.UserRole;
import me.dri.Catvie.domain.models.dto.LoginDTO;
import me.dri.Catvie.domain.models.dto.RegisterDTO;
import me.dri.Catvie.domain.models.dto.RegisterResponseDTO;
import me.dri.Catvie.domain.ports.interfaces.AuthenticationPort;
import me.dri.Catvie.domain.ports.interfaces.AuthenticationServicePort;
import me.dri.Catvie.domain.ports.interfaces.TokenServicesPort;
import me.dri.Catvie.infra.adapters.AuthenticationAdapter;
import me.dri.Catvie.infra.adapters.EncoderPasswordAdapter;
import me.dri.Catvie.infra.adapters.mapper.MapperEntityAdapter;
import me.dri.Catvie.infra.ports.EncoderPassword;
import me.dri.Catvie.infra.ports.MapperUserPort;
import me.dri.Catvie.infra.ports.UserRepositoryJPA;
import me.dri.Catvie.infra.tokens.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationManager;

import static  io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class AuthServicesTest {

    TokenServicesPort tokenServicesPort;
    UserRepositoryJPA repositoryJPA;
    MapperUserPort mapperUserPort;
    EncoderPassword encoderPassword;
    AuthenticationManager authenticationManager;

    AuthenticationPort authenticationPort;


    @BeforeEach
     void setup() {
        RestAssured.baseURI = "http://localhost:8080/";
        RestAssured.port = 8080;
        tokenServicesPort = new TokenService();
        mapperUserPort = new MapperEntityAdapter();
        encoderPassword = new EncoderPasswordAdapter();
        authenticationPort = new AuthenticationAdapter(tokenServicesPort, repositoryJPA, mapperUserPort, encoderPassword, authenticationManager);
    }

    @Test
    void registerTest() {
        RegisterDTO obj = new RegisterDTO("diego", "henrique", "diego@gmail.com", "123", "", UserRole.USER);
        given().when().contentType(ContentType.JSON).body(obj).when().post("/auth/v1/register")
                .then().statusCode(201).body("user", equalTo(obj.email()));
    }

    @Test
    void loginTest() {
        LoginDTO loginDTO = new LoginDTO("heenrikk3@gmail.com", "codigo123");
        given().when().contentType(ContentType.JSON).body(loginDTO).post("/auth/v1/login")
                .then().statusCode(201);
    }


}
