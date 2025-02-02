package runner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.base.LoginPage;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLoginTitleVisibility() {
        loginPage.verifyLoginTitle();
        Assert.assertTrue(loginPage.isLoginTitleDisplayed(),
                "The 'Login' title is not visible!");
    }

    @Test
    public void loginInAccount() {
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();
        loginPage.verifySuccessfulLogin();
        Assert.assertTrue(loginPage.isProfilePictureDisplayed(),
                "Profile picture is not displayed!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
