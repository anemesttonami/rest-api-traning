package specs;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;
import static utils.ReqresAllureFormatter.withCustomTemplates;

public class ReqresSpec {

    public static RequestSpecification getResourcesRequestSpec = with()
            .filter(withCustomTemplates())
            .basePath("{resource}")
            .pathParam("resource", "users")
            .queryParam("page", "1")
            .queryParam("per_page", "1")
            .header("x-api-key", "reqres-free-v1")
            .contentType(JSON);

    public static ResponseSpecification getResourcesResponseSpec = with()
            .expect()
            .statusCode(200)
            .contentType(JSON);


    public static RequestSpecification putUsersRequestSpec = with()
            .filter(withCustomTemplates())
            .contentType(ContentType.JSON)
            .basePath("users/{id}")
            .pathParam("id", "1")
            .header("x-api-key", "reqres-free-v1")
            .header("accept", "application/json")
            .log().uri();


    public static ResponseSpecification putUsersResponseSpec = with()
            .expect().statusCode(200)
            .and().log().body();

    public static RequestSpecification reqPostSuccessCreateSession = with().
            contentType(ContentType.JSON)
            .filter(withCustomTemplates())
            .header("x-api-key", "reqres-free-v1")
            .header("accept", "application/json")
            .basePath("/login")
            .log().uri().log().body();

    public static ResponseSpecification respPostSuccessCreateSession = with().
            expect()
            .statusCode(200)
            .log().all();

    public static ResponseSpecification respPostFailedCreateSession = with().
            expect()
            .statusCode(400)
            .log().all();
}
