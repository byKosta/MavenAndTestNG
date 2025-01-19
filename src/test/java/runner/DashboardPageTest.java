package runner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class DashboardPageTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        // Инициализация драйвера
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/");
        // Инициализация WebDriverWait
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
    public void dashboard(){
        WebElement h6Element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[@data-v-7b563373][@data-v-c286b6e5][@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")));
        String actualText = h6Element.getText();
        String expectedText = "Dashboard";
        Assert.assertEquals(actualText, expectedText, "The text of the h6 element does not match the expected value.");

    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
