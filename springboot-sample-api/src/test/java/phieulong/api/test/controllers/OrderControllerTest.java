package phieulong.api.test.controllers;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import phieulong.Application;
import phieulong.api.utils.TokenUtil;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = {"test"})
public class OrderControllerTest {

    @Autowired
    private TokenUtil tokenUtil;

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

    public String generateJwt(String userId, String role) {
        return "Bearer " + tokenUtil.createDefaultJwt(userId, new HashMap<>(){{put("role", role);}});
    }

    @Test
    public void testGetOrder_withoutToken_thenReturn401(){
        given()
                .when()
                .get("/v1/orders")
                .then().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void testGetOrder_withWrongToken_thenReturn401(){
        given()
                .when()
                .header(HttpHeaders.AUTHORIZATION, "WRONG_TOKEN")
                .get("/v1/orders")
                .then().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

//    @Test
//    public void testGetOrder_withValidToken_thenReturn200(){
//        given()
//                .when()
//                .header(HttpHeaders.AUTHORIZATION, generateJwt("99", "CUSTOMER"))
//                .get("/v1/orders")
//                .then().statusCode(HttpStatus.SC_OK)
//                .body("data", equalTo("Ok"));
//    }

    @Test
    public void testGetOrder_thenReturn200(){
        given()
                .when()
                .header(HttpHeaders.AUTHORIZATION, generateJwt("99", "CUSTOMER"))
                .get("/v1/orders")
                .then().statusCode(HttpStatus.SC_OK)
                .body("data.id", matchesRegex("d+"))
                .body("data.user_id", equalTo("99"))
                .body("data.created_at", matchesRegex("d+"))
                .body("data.updated_at", matchesRegex("d+"));
    }


    @Test
    public void testCreateOrder_thenReturn200(){
        given()
                .when()
                .header(HttpHeaders.AUTHORIZATION, generateJwt("99", "CUSTOMER"))
                .post("/v1/orders")
                .then().statusCode(HttpStatus.SC_OK)
                .body("data.id", matchesRegex("d+"))
                .body("data.user_id", equalTo("99"))
                .body("data.created_at", matchesRegex("d+"))
                .body("data.updated_at", matchesRegex("d+"));
    }
}
