package tests;

import io.restassured.RestAssured;
import models.Users;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.ReqresSpec.getResourcesRequestSpec;
import static specs.ReqresSpec.getResourcesResponseSpec;

public class ReqresTests {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://reqres.in/api/";
        RestAssured.basePath = "{resource}";
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

        assertEquals(users.getData().get(0).getEmail(), "george.bluth@reqres.in");

        System.out.println("---- deserialized Users class ---->\n" + users);
    }
}
