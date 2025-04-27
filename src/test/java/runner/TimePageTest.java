package runner;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TimePageTest {
    private WebDriver driver;
    private final String BASE_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    private final String USERNAME = "Admin";
    private final String PASSWORD = "admin123";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(BASE_URL);
    }

    @Test
    public void timePage() {
        WebElement usernameField = driver.findElement(By.name("username"));
        usernameField.sendKeys(USERNAME);

        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys(PASSWORD);

        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();

        WebElement dashboardHeader = driver.findElement(By.xpath("//h6[text()='Dashboard']"));
        Assert.assertTrue(dashboardHeader.isDisplayed(), "Login failed!");


        WebElement timeMenu = driver.findElement(By.xpath("//span[text()='Time']"));
        timeMenu.click();

        try {

            Assert.assertTrue(driver.getCurrentUrl().contains("/time"),
                    "Current URL: " + driver.getCurrentUrl());

            WebElement timeHeader = driver.findElement(
                    By.xpath("//h6[@class='oxd-text oxd-text--h6 orangehrm-main-title' and text()='Select Employee']"));
            Assert.assertTrue(timeHeader.isDisplayed(), "Time page header not displayed");


            WebElement employeeNameField = driver.findElement(
                    By.xpath("//label[text()='Employee Name']/following::input"));
            Assert.assertTrue(employeeNameField.isDisplayed(), "Employee name field not found");

        } catch (Exception e) {
            Assert.fail("Failed to verify Time page: " + e.getMessage());
        }
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
