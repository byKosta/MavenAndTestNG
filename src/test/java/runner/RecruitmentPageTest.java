package runner;

import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
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

import java.time.Duration;

public class RecruitmentPageTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String BASE_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    private final String USERNAME = "Admin";
    private final String PASSWORD = "admin123";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }


    @Test
    public void recruitmentPage(){
        // Логин
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

        usernameField.sendKeys(USERNAME);
        passwordField.sendKeys(PASSWORD);
        loginButton.click();

        // Переход на страницу Recruitment
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='Recruitment']")));
        WebElement recruitmentLink = driver.findElement(
                By.xpath("//span[text()='Recruitment']"));
        recruitmentLink.click();

        // Проверка что мы на странице Recruitment
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[text()='Recruitment']")));
        WebElement recruitmentHeader = driver.findElement(
                By.xpath("//h6[text()='Recruitment']"));

        Assert.assertTrue(recruitmentHeader.isDisplayed(),
                "Recruitment page header is not displayed");
        Assert.assertEquals(driver.getCurrentUrl(),
                "https://opensource-demo.orangehrmlive.com/web/index.php/recruitment/viewCandidates",
                "Current URL does not match Recruitment page URL");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
