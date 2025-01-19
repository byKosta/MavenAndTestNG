package runner;

import org.testng.annotations.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class PIMPageTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void login() {
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
    public void pimpage() {
        WebElement link = driver.findElement(By.xpath("//a[@href='/web/index.php/pim/viewPimModule']"));
        link.click();

        WebElement headerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h5[@data-v-7b563373][@data-v-b4b62742][@class='oxd-text oxd-text--h5 oxd-table-filter-title']")));
        String actualText = headerElement.getText();
        String expectedText = "Employee Information";
        Assert.assertEquals(actualText, expectedText, "The text of the h5 element does not match the expected value.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();

        }
    }
}
