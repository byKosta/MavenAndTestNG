package APITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;

public class PUTPetStoreAPITest {
    @Test
    public void testUpdatePet() {
        // Устанавливаем базовый URL
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        // Тело запроса
        String requestBody = """
        {
            "id": 1,
            "category": {
                "id": 2,
                "name": "Dogs"
            },
            "name": "Buddy",
            "photoUrls": ["https://example.com/image.jpg"],
            "tags": [
                {
                    "id": 1,
                    "name": "friendly"
                }
            ],
            "status": "available"
        }
        """;

        // Выполняем PUT-запрос
        Response response = given()
                .contentType(ContentType.JSON) // Устанавливаем тип контента
                .body(requestBody)            // Добавляем тело запроса
                .when()
                .put("/pet")
                .then()
                .statusCode(200)              // Проверяем успешный статус-код
                .extract().response();        // Извлекаем ответ

        // Логируем ответ
        System.out.println("Response: " + response.asString());

        // Проверяем, что объект в ответе совпадает с отправленным телом
        Assert.assertEquals(response.jsonPath().getString("name"), "Buddy", "Pet name mismatch!");
        Assert.assertEquals(response.jsonPath().getString("status"), "available", "Pet status mismatch!");
    }

}
