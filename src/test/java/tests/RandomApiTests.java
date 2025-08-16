package tests;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RandomApiTests {

    @Test
    void postAddObjects() {
        given()
                .headers("Content-Type", "application/json", "User-Agent", "Mozilla/5.0")
                .body("{\n" +
                        "   \"name\": \"Apple MacBook Pro 16\",\n" +
                        "   \"data\": {\n" +
                        "      \"year\": 2019,\n" +
                        "      \"price\": 1849.99,\n" +
                        "      \"CPU model\": \"Intel Core i9\",\n" +
                        "      \"Hard disk size\": \"1 TB\"\n" +
                        "   }\n" +
                        "}")
                .log().uri()

                .when()
                .post("https://api.restful-api.dev/objects")

                .then().log().all().statusCode(200)
                .body("name", is("Apple MacBook Pro 16"));
    }

    @Test
    void find64GbCapacity() {
        given().baseUri("https://api.restful-api.dev/").queryParam("id", 3, 5, 10)

                .when().get("/objects")

                .then()
                .statusCode(200)
                .body("$", hasSize(3))
                .body("name", hasItem("Apple iPad Mini 5th Gen"))
                .body("find { it.id == '10' }.data.Capacity", is("64 GB"));
    }

    @Test
    void find64GbCapacity2() {
        given()
                .queryParam("id", "3", "5", "10").
                when()
                .get("https://api.restful-api.dev/objects").
                then()
                .statusCode(200)
                .body("find { it.id == '10'}.data.Capacity", is("64 GB"));
    }

    @Test
    void assertHeadersTest() {
        given()
                .queryParam("id", "3", "5", "10").
                when()
                .get("https://api.restful-api.dev/objects").
                then()
                .statusCode(200)
                .header("Cf-Cache-Status", "DYNAMIC")
                .log().all();
    }

    @Test
    void measureResponceTimeTest() {
        given()
                .queryParam("id", "3", "5", "10").
                when()
                .get("https://api.restful-api.dev/objects").
                then()
                .statusCode(200)
                .time(Matchers.lessThan(Long.valueOf("2")), TimeUnit.SECONDS)
                .log().all();
    }

    @Test
    void forEachObjectAssertThatLengthMoreThan3() {
        given()
                .queryParam("id", "3", "5", "10").
                when()
                .get("https://api.restful-api.dev/objects").
                then()
                .statusCode(200)
                .body("name.every { it.length() > 3 }", is(true));

    }

}
