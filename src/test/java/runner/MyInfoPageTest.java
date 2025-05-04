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
public class MyInfoPageTest {
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
    @Test(priority = 1)
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

    @Test(priority = 2)
    public void myinfo() {
        WebElement myInfoElement = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@class='oxd-main-menu-item' and .//span[text()='My Info']]")
        ));
        myInfoElement.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement textElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[contains(@class, 'oxd-text--h6') and text()='PIM']"))
        );
        Assert.assertTrue(textElement.isDisplayed(), "The text 'PIM' is not visible on the page.");


    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
