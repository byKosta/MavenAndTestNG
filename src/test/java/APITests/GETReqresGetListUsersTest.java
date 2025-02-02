package APITests;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GETReqresGetListUsersTest {
    @Test
    public void getListUsersTest() {
        // Устанавливаем базовый URL
        RestAssured.baseURI = "https://reqres.in";

        // Выполняем GET-запрос
        Response response = given()
                .when()
                .get("/api/users?page=2")                      // Указываем эндпоинт
                .then()
                .statusCode(200)                              // Проверяем статус код 200 (OK)
                .body("pages", equalTo(2))                    // Проверяем, что "page" = 2
                .body("per_page", equalTo(6))                // Проверяем, что "per_page" = 6
                .body("total", equalTo(12))                  // Проверяем, что "total" = 12
                .body("total_pages", equalTo(2))             // Проверяем, что "total_pages" = 2
                .body("data", not(empty()))                  // Проверяем, что "data" не пустой
                .body("data[0].id", equalTo(7))              // Проверяем первый объект в "data"
                .extract().response();                      // Извлекаем полный ответ

        // Логируем ответ для отладки
        System.out.println("Response: " + response.asString());

        // Дополнительные проверки
        Assert.assertNotNull(response.jsonPath().getList("data"), "Data list should not be null!");
        Assert.assertEquals(response.jsonPath().getString("support.url"),
                "https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral",
                "Support URL is incorrect!");
    }
}
