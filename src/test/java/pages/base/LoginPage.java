package pages.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;


    @FindBy(xpath = "//h5[text()='Login']")
    private WebElement loginTitle;

    @FindBy(name = "Admin")
    private WebElement usernameField;

    @FindBy(name = "admin123")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//img[@alt='profile picture']")
    private WebElement profilePicture;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }


    public void verifyLoginTitle() {
        wait.until(ExpectedConditions.visibilityOf(loginTitle));
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameField)).sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void verifySuccessfulLogin() {
        wait.until(ExpectedConditions.visibilityOf(profilePicture));
    }

    public boolean isLoginTitleDisplayed() {
        return loginTitle.isDisplayed();
    }

    public boolean isProfilePictureDisplayed() {
        return profilePicture.isDisplayed();
    }
}
