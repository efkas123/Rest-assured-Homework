package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static tests.TestBase.MyApiKey;

public class ListUsersSpec {
    public static RequestSpecification listUsersRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .log().headers()
            .queryParam("page", "2")
            .header("x-api-key", MyApiKey);


public static ResponseSpecification listUsersResponseSpec = new ResponseSpecBuilder()
        .expectStatusCode(200)
        .log(STATUS)
        .log(BODY)
        .build();

}
