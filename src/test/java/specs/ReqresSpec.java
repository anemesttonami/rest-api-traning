package specs;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;
import static utils.ReqresAllureFormatter.withCustomTemplates;

public class ReqresSpec {

    public static RequestSpecification getResourcesRequestSpec =
            with()
                    .filter(withCustomTemplates())
                    .pathParam("resource", "users")
                    .queryParam("page", "1")
                    .queryParam("per_page", "1")
                    .header("x-api-key", "reqres-free-v1")
                    .contentType(JSON);

    public static ResponseSpecification getResourcesResponseSpec =
            with()
                    .expect()
                    .statusCode(200)
                    .contentType(JSON);

}
