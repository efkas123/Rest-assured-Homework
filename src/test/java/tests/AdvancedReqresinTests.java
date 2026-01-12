package tests;

import models.ListUsers;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.ListUsersSpec.listUsersRequestSpec;
import static specs.ListUsersSpec.listUsersResponseSpec;

public class AdvancedReqresinTests extends TestBase {
    @Test
    @Tag("advanced")
    void getListUsersTest() {
        ListUsers listUsers = new ListUsers();
        ListUsers response = step("Make request", ()->
                given(listUsersRequestSpec)
                .when()
                .queryParam("page", "2")
                .get("/users")
                .then()
                        .spec(listUsersResponseSpec)
                .extract().as(ListUsers.class));

        step("Check response", ()->
            assertEquals(response.getPage(), 2));
    }


}
