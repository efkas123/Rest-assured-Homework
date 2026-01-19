package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static tests.TestBase.MyApiKey;

public class DeleteUserSpec {
    public static RequestSpecification deleteUserRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .header("x-api-key", MyApiKey);

    public static ResponseSpecification deleteUserResponseSpec = new ResponseSpecBuilder()
            .log(BODY)
            .build();
}
