package runner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;


public class LoginTest {


    WebDriver driver;
    WebDriverWait wait;

    @Test
    public void testLoginTitleVisibility() {
        // Set up WebDriver and open the website
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.manage().window().maximize();

        // Initialize WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Find the <h5> element
        WebElement h5Element = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h5[text()='Login']")));

        // Validate that the element is displayed
        Assert.assertTrue(h5Element.isDisplayed(), "The 'Login' title is not visible!");

        driver.quit();
    }


}