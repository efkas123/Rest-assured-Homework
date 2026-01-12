package tests;

import models.ListUsers;
import models.PostCreate;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import specs.PostCreateSpec;


import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.ListUsersSpec.listUsersRequestSpec;
import static specs.ListUsersSpec.listUsersResponseSpec;
import static specs.PostCreateSpec.postCreateRequestSpec;
import static specs.PostCreateSpec.postCreateResponseSpec;

public class AdvancedReqresinTests extends TestBase {
    @Test
    @Tag("advanced")
    void getListUsersTest() {
        ListUsers listUsers = new ListUsers();
        ListUsers response = step("Make request", ()->
                given(listUsersRequestSpec)
                .when()
                .get("/users")
                .then()
                        .spec(listUsersResponseSpec)
                .extract().as(ListUsers.class));

        step("Check response", ()->
            assertEquals(response.getPage(), 2));
    }

    @Test
    @Tag("advanced")
    void postCreateTest() {
        PostCreate postCreate = new PostCreate();
        PostCreate response = step("Make request", ()->
        given(postCreateRequestSpec)
                .when()
                .post("/users")
                .then()
                .spec(postCreateResponseSpec)
                .extract().as(PostCreate.class));

        step("Check response", ()->
                assertEquals(response.getJob(), "leader"));
    }

    @Test
    @Tag("advanced")
    void deleteUserTest() {
        given()
                .header("x-api-key", MyApiKey)
                .when()
                .delete("/users/2")
                .then()
                .log().body()
                .statusCode(204);
    }


}
