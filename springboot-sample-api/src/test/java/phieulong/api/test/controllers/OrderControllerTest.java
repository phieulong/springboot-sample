package phieulong.api.test.controllers;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import phieulong.Application;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = {"test"})
public class OrderControllerTest {

    @LocalServerPort
    protected int serverPort;

    @BeforeEach
    public void setUp() {
        RestAssured.port = this.serverPort;

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(MediaType.APPLICATION_JSON_VALUE)
                .setAccept(MediaType.APPLICATION_JSON_VALUE)
                .build();

    }

    @Test
    public void testCallApi(){
        given().when().get("/v1/orders").then().body("data", equalTo("Ok"));
    }
}
