package OldTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class MainTest {

@Test
    public void test() {
    WebDriver driver = new ChromeDriver();

    driver.get("https://www.selenium.dev/selenium/web/web-form.html");

    driver.getTitle();

    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

    WebElement textBox = driver.findElement(By.name("my-text"));
    WebElement submitButton = driver.findElement(By.cssSelector("button"));

    textBox.sendKeys("Selenium");
    submitButton.click();

    WebElement message = driver.findElement(By.id("message"));
    Assert.assertEquals(message.getText(),  "Received!");

    driver.quit();
    }
}

