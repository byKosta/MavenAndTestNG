package runner;


import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;




public class Login {

    @Test
    public void testOrangeHrmLogoIsVisible() {
        WebDriver driver = new ChromeDriver();
        // Переход на сайт
        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.manage().window().maximize();



        driver.quit();
    }




}