package APITests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class GETPetStoreAPITest {
    @Test
    public void testFindPetsByStatus() {
        // Устанавливаем базовый URL
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        // Выполняем GET-запрос с параметрами status
        Response response = given()
                .queryParam("status", "available,pending,sold") // Передаем значения через запятую
                .when()
                .get("/pet/findByStatus")
                .then()
                .statusCode(200)                                // Проверяем статус-код 200
                .body("$", not(empty()))                        // Проверяем, что массив ответа не пустой
                .body("status", everyItem(notNullValue()))      // Проверяем, что каждый объект имеет статус
                .extract().response();                         // Извлекаем ответ

        // Логируем ответ для отладки
        System.out.println("Response: " + response.asString());

        // Дополнительные проверки через JSONPath
        Assert.assertTrue(response.jsonPath().getList("status").contains("available"),
                "Response should contain pets with 'available' status!");
    }

}
