package tests;

import io.restassured.response.Response;
import models.ListUsers;
import models.PostCreate;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.DeleteUserSpec.deleteUserRequestSpec;
import static specs.DeleteUserSpec.deleteUserResponseSpec;
import static specs.ListUsersSpec.listUsersRequestSpec;
import static specs.ListUsersSpec.listUsersResponseSpec;
import static specs.PostCreateSpec.postCreateRequestSpec;
import static specs.PostCreateSpec.postCreateResponseSpec;

public class AdvancedReqresinTests extends TestBase {
    @Test
    @Tag("advanced")
    void getListUsersTest() {
        ListUsers listUsers = new ListUsers();
        ListUsers response = step("Make request", () ->
                given(listUsersRequestSpec)
                        .when()
                        .get("/users")
                        .then()
                        .spec(listUsersResponseSpec)
                        .body("page", is(2))
                        .body("data[0].email", is("michael.lawson@reqres.in"))
                        .body("support", hasKey("url"))
                        .extract().as(ListUsers.class));

        step("Check response", () ->
                assertEquals(response.getPage(), 2));
    }

    @Test
    @Tag("advanced")
    void postCreateTest() {
        PostCreate postCreate = new PostCreate();
        PostCreate response = step("Make request", () ->
                given(postCreateRequestSpec)
                        .when()
                        .post("/users")
                        .then()
                        .spec(postCreateResponseSpec)
                        .body("name", is("morpheus"))
                        .body("job", is("leader"))
                        .extract().as(PostCreate.class));

        step("Check response", () ->
                assertEquals(response.getJob(), "leader"));
    }

    @Test
    @Tag("advanced")
    void deleteUserTest() {

        Response response;

        response = step("Make request", () ->
                given(deleteUserRequestSpec)
                        .when()
                        .delete("/users/2")
                        .then()
                        .spec(deleteUserResponseSpec))
                .statusCode(204)
                .extract()
                .response();

        step("Check response body is empty", () ->
                assertEquals("", response.getBody().asString())
        );
    }


}
