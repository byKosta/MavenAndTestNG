package OldTests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;


public class SaturdayTest {
    WebDriver driver = new ChromeDriver();

    @BeforeMethod
    public void baseURL() {
        driver.get("https://www.umgdy.gov.pl/");
        driver.manage().window().maximize();
    }

    @Test

    public void test() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Ожидание элемента <h1> с указанным текстом
        WebElement h1Element = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h1[text()='Urząd Morski w Gdyni']") // XPath для поиска элемента <h1> с нужным текстом
        ));
        // Получение текста из элемента
        String actualText = h1Element.getText();
        System.out.println("Found h1 text: " + actualText);

        // Проверка текста
        Assert.assertEquals(actualText, "Urząd Morski w Gdyni");
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }

}
