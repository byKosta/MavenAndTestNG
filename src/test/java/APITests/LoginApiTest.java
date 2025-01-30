package APITests;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginApiTest {

    // Базовый URL для API
    private static final String BASE_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    @BeforeClass
    public void setup() {
        // Устанавливаем базовый URL для RestAssured
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testLogin() {
        // Создаем тело запроса (логин и пароль)
        String requestBody = "{\n" +
                "  \"username\": \"Admin\",\n" +
                "  \"password\": \"admin123\"\n" +
                "}";

        // Отправляем POST запрос с телом и проверяем статус код
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post();

        // Проверяем, что ответ имеет статус 200 (успех)
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Login failed, expected status code 200.");

        // Проверяем, что ответ содержит ожидаемую информацию (например, токен или сообщение об успехе)
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("expected_success_response_or_token"), "Response body does not contain expected content.");
    }
}

