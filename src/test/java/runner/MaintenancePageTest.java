package runner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class MaintenancePageTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        // Set up WebDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Test
    public void LoginInAccount() {

        WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.name("username")));
        usernameField.sendKeys("Admin");
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.name("password")));
        passwordField.sendKeys("admin123");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@type='submit']")));
        loginButton.click();

        WebElement profilePicture = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//img[@alt='profile picture']")));
        Assert.assertTrue(profilePicture.isDisplayed(), "Profile picture is not displayed!");
    }
    @Test
    public void testMaintenancePage() {
        // Navigate to the Maintenance Page
        WebElement maintenanceLink = driver.findElement(By.xpath("//span[text()='Maintenance']"));
        maintenanceLink.click();

        // Verify the "Administrator Access" text is displayed
        WebElement adminAccessText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Administrator Access']"))
        );
        Assert.assertTrue(adminAccessText.isDisplayed(), "The 'Administrator Access' text is not found on the Maintenance page.");

        // Enter an incorrect password
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("Password123");

        // Click the Confirm button
        WebElement confirmButton = driver.findElement(By.xpath("//button[text()=' Confirm ']"));
        confirmButton.click();

        // Verify the "Invalid credentials" error message is displayed
        WebElement errorMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Invalid credentials']"))
        );
        Assert.assertTrue(errorMessage.isDisplayed(), "The 'Invalid credentials' error message is not displayed.");
    }

    @AfterClass
    public void tearDown() {
        // Close the browser after the test completes
        if (driver != null) {
            driver.quit();
        }
    }
}

