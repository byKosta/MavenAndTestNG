package APITests;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class POSTRequestCreateUserTest {
    @Test
    public void createUserTest() {
        // Устанавливаем базовый URL
        RestAssured.baseURI = "https://reqres.in";

        // Тело запроса
        String requestBody = """
            {
                "name": "morpheus",
                "job": "leader"
            }
        """;

        // Выполняем POST-запрос
        Response response = given()
                .header("Content-Type", "application/json") // Указываем тип содержимого
                .body(requestBody)                          // Передаём тело запроса
                .when()
                .post("/api/users")                         // Указываем эндпоинт
                .then()
                .statusCode(201)                            // Проверяем, что статус код 201 (Created)
                .body("name", equalTo("morpheus"))          // Проверяем значение поля "name"
                .body("job", equalTo("leader"))             // Проверяем значение поля "job"
                .extract().response();                      // Извлекаем полный ответ

        // Дополнительные проверки
        Assert.assertNotNull(response.jsonPath().getString("id"), "ID should not be null!");
        Assert.assertNotNull(response.jsonPath().getString("createdAt"), "createdAt should not be null!");

        // Логируем ответ
        System.out.println("Response: " + response.asString());
    }
}
