package cucumber;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import java.time.Duration;

public class FreestyleJobSteps {

    private WebDriver driver;
    private WebElement h1Element;

    @Given("I navigate to UMG Gdyni website")
    public void navigateToWebsite() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.umgdy.gov.pl/");
    }

    @When("I wait for header element to load")
    public void waitForHeader() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            h1Element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h1[normalize-space()='Urząd Morski w Gdyni']")));
        } catch (TimeoutException e) {
            Assert.fail("Header element not found within 15 seconds");
            driver.quit();
        }

    }
}


