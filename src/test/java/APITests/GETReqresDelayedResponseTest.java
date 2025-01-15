package APITests;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GETReqresDelayedResponseTest {
    @Test
    public void getDelayedResponseTest() {
        // Устанавливаем базовый URL
        RestAssured.baseURI = "https://reqres.in";

        // Выполняем GET-запрос с ожиданием
        Response response = given()
                .when()
                .get("/api/users?page=2")                  // Указываем эндпоинт с задержкой
                .then()
                .statusCode(200)                            // Проверяем, что статус код 200 (OK)
                .header("x-runtime-ms", notNullValue())     // Проверяем наличие заголовка "x-runtime-ms"
                .extract().response();                      // Извлекаем полный ответ

        // Проверка времени задержки
        int runtimeMs = response.getHeader("x-runtime-ms") != null
                ? Integer.parseInt(response.getHeader("x-runtime-ms"))
                : 0;
        Assert.assertTrue(runtimeMs > 0, "Response time should be greater than 0 ms!");

        // Логируем ответ
        System.out.println("Response: " + response.asString());
    }
}
