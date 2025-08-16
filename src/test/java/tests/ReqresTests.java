package tests;

import io.restassured.RestAssured;
import models.UserSession;
import models.Users;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.ReqresSpec.*;

public class ReqresTests {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://reqres.in/api/";
    }

    @Test
    void getResourcesTest() {
        Users users = given().spec(getResourcesRequestSpec)
                .log()
                .uri()
                .when()
                .get()
                .then()
                .spec(getResourcesResponseSpec)
                .extract().as(Users.class);

        assertEquals("george.bluth@reqres.in", users.getData().get(0).getEmail());

        System.out.println(" ---- deserialized Users class ---->\n " + users);
    }

    @Test
    void putUsersIdTest() {
        given()
                .spec(putUsersRequestSpec)
                .when()
                .put()
                .then()
                .spec(putUsersResponseSpec);
    }

    @Test
    void postSuccessCreateSessionTest() {

        UserSession createdSession = new UserSession();

        createdSession.setEmail("eve.holt@reqres.in");
        createdSession.setPassword("pistol");

        given()
                .spec(reqPostSuccessCreateSession)
                .body(createdSession)
                .when()
                .post()
                .then()
                .spec(respPostSuccessCreateSession)
                .body("$", hasKey("token"))
                .body("token", equalTo("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void postFailedCreateSessionTest() {

        UserSession createdSession = new UserSession();

        createdSession.setUsername("string");
        createdSession.setEmail("eve.holt@reqres.in");
        createdSession.setPassword("pistol");

        given()
                .spec(reqPostSuccessCreateSession)
                .body(createdSession)
                .when()
                .post()
                .then()
                .spec(respPostFailedCreateSession);
    }
}
