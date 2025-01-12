package OldTests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;




public class SundayTest {
    WebDriver driver = new ChromeDriver();

    @BeforeMethod
    public void baseURL() {
        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.manage().window().maximize();
    }

    @Test

    public void test() {


    }
    @Test
    public void test1() {


    }
    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }

}
