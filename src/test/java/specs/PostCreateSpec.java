package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static org.hamcrest.Matchers.is;
import static tests.TestBase.MyApiKey;
import static tests.TestBase.postCreateJSON;

public class PostCreateSpec {
    public static RequestSpecification postCreateRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .header("x-api-key", MyApiKey)
            .body(postCreateJSON)
            .contentType("application/json");

    public static ResponseSpecification postCreateResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(STATUS)
            .log(BODY)
            .expectBody("name", is("morpheus"))
            .expectBody("job", is("leader"))
            .build();


}
