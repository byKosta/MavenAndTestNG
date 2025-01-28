package runner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class AdminPageTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set up WebDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testAdminPage() {
        // Open the OrangeHRM website
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // Enter username and password
        WebElement usernameField = driver.findElement(By.name("username"));
        usernameField.sendKeys("Admin");

        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("admin123");

        // Click the Login button
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();

        // Navigate to the Admin page
        WebElement adminLink = driver.findElement(By.xpath("//span[text()='Admin']"));
        adminLink.click();

        // Wait for the "System Users" text to appear on the page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement systemUsersText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='System Users']"))
        );

        // Verify that the "System Users" text is displayed
        Assert.assertTrue(systemUsersText.isDisplayed(), "The 'System Users' text is not found on the Admin page.");
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser after the test completes
        if (driver != null) {
            driver.quit();
        }
    }
}