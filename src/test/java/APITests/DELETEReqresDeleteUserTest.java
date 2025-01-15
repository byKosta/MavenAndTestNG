package APITests;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class DELETEReqresDeleteUserTest {
    @Test
    public void deleteUserTest() {
        // Устанавливаем базовый URL
        RestAssured.baseURI = "https://reqres.in";

        // Выполняем DELETE-запрос
        Response response = given()
                .when()
                .delete("/api/users/2")                      // Указываем эндпоинт
                .then()
                .statusCode(204)                            // Проверяем, что статус код 204 (No Content)
                .extract().response();                      // Извлекаем полный ответ

        // Дополнительная проверка: тело ответа должно быть пустым
        Assert.assertEquals(response.asString().length(), 0, "Response body is not empty!");

        // Логируем ответ
        System.out.println("Response status code: " + response.getStatusCode());
    }
}
