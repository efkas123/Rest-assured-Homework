import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.basePath;

public class TestBase {

    public String MyApiKey = "reqres_d16cdb15190f4682a7f12e8ec16de76e";

    public String postCreateJSON = """
            {
                "name": "morpheus",
                "job": "leader"
            }
            """;

    public String putUpdateJSON = """
            {
                "name": "morpheus",
                "job": "zion resident"
            }
            """;

    public String registrationSuccessfulJson = """
            {
                "email": "eve.holt@reqres.in",
                "password": "pistol"
            }
            """;

    public String registrationUnsuccessfulJson = """
            {
                "email": "sydney@fife"
            }
            """;

    public String loginSuccessfulJson = """
            {
                "email": "eve.holt@reqres.in",
                "password": "cityslicka"
            }
            """;

    public String loginUnsuccessfulJson = """
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
