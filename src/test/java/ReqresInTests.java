import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ReqresInTests extends TestBase {


    //Как я понимаю, reqres перестал быть открытым api, поэтому пришлось создавать аккаунт и получать api ключ.
    @Test
    void getListUsersTest() {
        given()
                .header("x-api-key", MyApiKey)
        .when()
                .queryParam("page", "2")
                .get("/users")
        .then()
                .log().body()
                .statusCode(200)
                .body("page", is(2))
                .body("data[0].email", is("michael.lawson@reqres.in"))
                .body("support", hasKey("url"));

    }

    @Test
    void getSingleUserTest() {
        given()
                .header("x-api-key", MyApiKey)
        .when()
                .get("/users/2")
        .then()
                .log().body()
                .statusCode(200)
                .body("data.id", is(2))
                .body("support.url", is("https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral"));
    }

    @Test
    void getSingleUserNotFoundTest() {
        given()
                .header("x-api-key", MyApiKey)
        .when()
                .get("/users/23")
        .then()
                .log().body()
                .statusCode(404);
    }

    @Test
    void getListResourceTest() {
        given()
                .header("x-api-key", MyApiKey)
        .when()
                .get("/unknown")
        .then()
                .log().body()
                .statusCode(200)
                .body("page", is(1))
                .body("data[0].name", is("cerulean"));
    }

    @Test
    void getSingleResourceTest() {
        given()
                .header("x-api-key", MyApiKey)
        .when()
                .get("/unknown/2")
        .then()
                .log().body()
                .statusCode(200)
                .body("data.year", is(2001));
    }

    @Test
    void getSingleResourceNotFoundTest() {
        given()
                .header("x-api-key", MyApiKey)
        .when()
                .get("/unknown/23")
        .then()
                .log().body()
                .statusCode(404);
    }

    @Test
    void postCreateTest() {
        given()
                .header("x-api-key", MyApiKey)
                .body(postCreateJSON)
                .contentType("application/json")
        .when()
                .post("/users")
        .then()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }

    @Test
    void putUpdateTest() {
        given()
                .header("x-api-key", MyApiKey)
                .body(putUpdateJSON)
                .contentType("application/json")
        .when()
                .put("/users/2")
        .then()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"));

    }

    @Test
    void patchUpdateTest() {
        given()
            .header("x-api-key", MyApiKey)
            .body(putUpdateJSON)
            .contentType("application/json")
        .when()
            .patch("/users/2")
        .then()
            .log().body()
            .statusCode(200)
            .body("name", is("morpheus"))
            .body("job", is("zion resident"));
    }

    @Test
    void deleteUserTest() {
        given()
            .header("x-api-key", MyApiKey)
        .when()
            .delete("/users/2")
        .then()
            .log().body()
            .statusCode(204);
    }

    @Test
    void postRegistrationSuccessfulTest() {
        given()
                .header("x-api-key", MyApiKey)
                .body(registrationSuccessfulJson)
                .contentType("application/json")
        .when()
                .post("/register")
        .then()
                .log().body()
                .statusCode(200)
                .body("id", is(4))
                .body("token", notNullValue())
                //Тут пришлось применить GPT. Пока не знаю синтаксис регулярок
                .body("token", matchesPattern(".*\\S.*"))  //Не пустая строка
                .body("token", matchesPattern(".{10,}")) // Не менее 10 символов
                .body("token", matchesPattern("^[A-Za-z0-9]+$")); //Только определённые символы
                /*Понимаю, что такие проверки токенов
                хотелось бы вынести в отдельную утилиту,
                но пока не знаю как, поэтому смотрю курс дальше */

    }

    @Test
    void postRegistrationUnsuccessfulTest() {
        given()
                .header("x-api-key", MyApiKey)
                .body(registrationUnsuccessfulJson)
                .contentType("application/json")
        .when()
                .post("/register")
        .then()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void postLoginSuccessfulTest() {
        given()
                .header("x-api-key", MyApiKey)
                .body(loginSuccessfulJson)
                .contentType("application/json")
        .when()
                .post("/login")
        .then()
                .log().body()
                .statusCode(200)
                .body("token", matchesPattern(".*\\S.*"))
                .body("token", matchesPattern(".{10,}"))
                .body("token", matchesPattern("^[A-Za-z0-9]+$"));
    }

    @Test
    void postLoginUnsuccessfulTest() {
        given()
                .header("x-api-key", MyApiKey)
                .body(loginUnsuccessfulJson)
                .contentType("application/json")
        .when()
                .post("/login")
        .then()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void getDelayedResponse() {
        given()
                .header("x-api-key", MyApiKey)
        .when()
                .queryParam("delay", "3")
                .get("/users")
        .then()
                .log().body()
                .statusCode(200)
                .body("data[0].id", is(1))
                .body("data[0].first_name", is("George"));
    }
}
