package APITests;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PATCHReqresPatchUserTest {
    @Test
    public void patchUserTest() {
        // Устанавливаем базовый URL
        RestAssured.baseURI = "https://reqres.in";

        // Тело запроса
        String requestBody = """
            {
                "name": "morpheus",
                "job": "matrix leader"
            }
        """;

        // Выполняем PATCH-запрос
        Response response = given()
                .header("Content-Type", "application/json") // Указываем тип содержимого
                .body(requestBody)                          // Передаём тело запроса
                .when()
                .patch("/api/users/2")                      // Указываем эндпоинт
                .then()
                .statusCode(200)                            // Проверяем, что статус код 200 (OK)
                .body("name", equalTo("morpheus"))          // Проверяем значение поля "name"
                .body("job", equalTo("matrix leader"))      // Проверяем значение поля "job"
                .extract().response();                      // Извлекаем полный ответ

        // Дополнительные проверки
        Assert.assertNotNull(response.jsonPath().getString("updatedAt"), "updatedAt should not be null!");

        // Логируем ответ
        System.out.println("Response: " + response.asString());
    }
}
