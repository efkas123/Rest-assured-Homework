package tests;

import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.basePath;

public class TestBase {

    public static final String MyApiKey = "reqres_d16cdb15190f4682a7f12e8ec16de76e";

    public static String postCreateJSON = """
            {
                "name": "morpheus",
                "job": "leader"
            }
            """;

    public static String putUpdateJSON = """
            {
                "name": "morpheus",
                "job": "zion resident"
            }
            """;

    public static String registrationSuccessfulJson = """
            {
                "email": "eve.holt@reqres.in",
                "password": "pistol"
            }
            """;

    public static String registrationUnsuccessfulJson = """
            {
                "email": "sydney@fife"
            }
            """;

    public static String loginSuccessfulJson = """
            {
                "email": "eve.holt@reqres.in",
                "password": "cityslicka"
            }
            """;

    public static String loginUnsuccessfulJson = """
            {
                "email": "peter@klaven"
            }
            """;

        @BeforeAll
        static void setUp(){
            baseURI = "https://reqres.in";
            basePath = "/api";

    }


}
