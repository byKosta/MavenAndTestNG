package cucumber;

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
    public void navigate_to_website() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.umgdy.gov.pl/");
    }

    @When("I wait for header element to load")
    public void wait_for_header() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        h1Element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[normalize-space()='Urząd Morski w Gdyni']")));
    }

    @Then("The header text should be {string}")
    public void verify_header_text(String expectedText) {
        Assert.assertEquals(
                h1Element.getText().trim(),
                expectedText,
                "Текст заголовка не соответствует ожидаемому"
        );
        driver.quit();
    }
}
