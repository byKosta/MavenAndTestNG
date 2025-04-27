package runner;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class TimePageTest {
    private static WebDriver driver;
    private static final String BASE_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    private static final String USERNAME = "Admin";
    private static final String PASSWORD = "admin123";
    private static WebDriverWait wait;

    @BeforeSuite
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get(BASE_URL);

        // Выполняем логин один раз перед всеми тестами
        login();
    }

    private void login() {
        WebElement usernameField = driver.findElement(By.name("username"));
        usernameField.sendKeys(USERNAME);

        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys(PASSWORD);

        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[text()='Dashboard']")));
    }

    @Test(priority = 1)
    public void timePage() {
        // Переходим на страницу Time
        WebElement timeMenu = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Time']")));
        timeMenu.click();

        try {
            // Проверяем URL
            wait.until(ExpectedConditions.urlContains("/time"));

            // Проверяем заголовок
            WebElement timeHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h6[@class='oxd-text oxd-text--h6 orangehrm-main-title' and text()='Select Employee']")));
            Assert.assertTrue(timeHeader.isDisplayed(), "Time page header not displayed");

            // Проверяем поле Employee Name
            WebElement employeeNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//label[text()='Employee Name']/following::input")));
            Assert.assertTrue(employeeNameField.isDisplayed(), "Employee name field not found");

        } catch (Exception e) {
            Assert.fail("Failed to verify Time page: " + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void testNavigationThroughTimeMenu() {
        // Уже находимся на странице Time после выполнения первого теста
        try {
            // 1. Кликаем Project Info и выбираем Customers
            WebElement projectInfoTab = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(@class, 'oxd-topbar-body-nav-tab-item') and text()='Project Info ']")));
            projectInfoTab.click();

            WebElement customersMenu = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@class='oxd-topbar-body-nav-tab-link' and text()='Customers']")));
            customersMenu.click();

            // Проверяем заголовок Customers
            WebElement customersHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h6[@class='oxd-text oxd-text--h6 orangehrm-main-title' and text()='Customers']")));
            Assert.assertTrue(customersHeader.isDisplayed(), "Customers page not opened");

            // 2. Кликаем Timesheets и выбираем My Timesheets
            WebElement timesheetsTab = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(@class, 'oxd-topbar-body-nav-tab-item') and text()='Timesheets ']")));
            timesheetsTab.click();

            WebElement myTimesheetsMenu = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@class='oxd-topbar-body-nav-tab-link' and text()='My Timesheets']")));
            myTimesheetsMenu.click();

            // Проверяем заголовок My Timesheet
            WebElement myTimesheetHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h6[@class='oxd-text oxd-text--h6 orangehrm-main-title' and text()='My Timesheet']")));
            Assert.assertTrue(myTimesheetHeader.isDisplayed(), "My Timesheets page not opened");

            // 3. Возвращаемся в раздел Time
            WebElement timeMenu = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name' and text()='Time']")));
            timeMenu.click();

            // Проверяем что вернулись на страницу Time
            wait.until(ExpectedConditions.urlContains("/time"));

        } catch (Exception e) {
            Assert.fail("Test failed: " + e.getMessage());
        }
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
