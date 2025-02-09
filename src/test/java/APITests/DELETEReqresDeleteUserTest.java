package APITests;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class DELETEReqresDeleteUserTest {
    @Test
    public void deleteUserTest() {

        RestAssured.baseURI = "https://reqres.in";


        Response response = given()
                .when()
                .delete("/api/users/2")
                .then()
                .statusCode(204)
                .extract().response();


        Assert.assertEquals(response.asString().length(), 0, "Response body is not empty!");


        System.out.println("Response status code: " + response.getStatusCode());
    }
}
